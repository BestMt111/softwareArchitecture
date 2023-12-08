import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Input implements Observer {

    private ArrayList<String> lineTxt = new ArrayList<String>();
    private String fileName;

    public ArrayList<String> getLineTxt() {
        return lineTxt;
    }

    //构造函数默认为从文件路径读取
    public Input(String fileName) {
        this.fileName = fileName;
    }

    public void readFromCommandLine(String host, int port) {

    }
    public void readFromSocket(String host, int port) {
        try(Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // 发送请求数据
            System.out.println("request data");

            // 读取响应数据
            String response;
            while ((response = in.readLine()) != null) {
                lineTxt.add(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromMessageMiddleware(String queueName) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // 设置RabbitMQ服务器地址

        try (Connection connection = (Connection) factory.newConnection();
             Channel channel = (Channel) connection.createClob()) {

            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicConsume(queueName, true, (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                lineTxt.add(message);
            }, consumerTag -> {});

            while (true) {
                // 持续监听队列中的消息，将接收到的消息添加到数据集合中
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromDatabase(String databaseURL, String query) {
        try (Connection connection = DriverManager.getConnection(databaseURL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                // 读取数据库中的记录并将其添加到数据集合中
                String data = resultSet.getString("column_name");
                lineTxt.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toDo() {
        BufferedReader inputFile = null;
        try {
            inputFile = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = inputFile.readLine()) != null) {
                lineTxt.add(line);
            }
        } catch (FileNotFoundException e) {
            // 该路径下的文件不存在或无法打开
            e.printStackTrace();
        } catch (IOException e) {
            // 输入/输出错误
            e.printStackTrace();
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    // 输入/输出错误
                    e.printStackTrace();
                }
            }
        }
    }
}