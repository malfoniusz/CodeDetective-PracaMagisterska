package staticc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class TotalLines {

    public static int totalLines(File file) {
        int totalLines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                totalLines++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return totalLines;
    }

}
