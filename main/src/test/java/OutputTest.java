import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class OutputTest {
    @Test
    public void testMethod() {
        File file = new File("D:\\Lessons\\softwareArch\\project\\file\\test_out.txt");
        try (FileWriter writer = new FileWriter(file)) {
            Alphabetizer alphabetizer = null;
            for (String kwic : alphabetizer.getKwicList()) {
                writer.write(kwic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
