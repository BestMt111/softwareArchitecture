import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Input implements Observer {

    private ArrayList<String> lineTxt = new ArrayList<String>();
    private String fileName;

    public ArrayList<String> getLineTxt() {
        return lineTxt;
    }

    public Input(String fileName) {
        this.fileName = fileName;
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