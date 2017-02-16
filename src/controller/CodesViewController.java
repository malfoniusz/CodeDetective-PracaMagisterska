package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CodesViewController implements Initializable {

    @FXML TextFlow iCode1;
    @FXML TextFlow iCode2;

    final File file1 = new File("F:\\Desktop\\Game.java");
    final File file2 = new File("F:\\Desktop\\Drawing.java");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCodes(file1, 32, 52, file2, 36, 49);
    }

    public void setCodes(File file1, int file1FromLine, int file1ToLine, File file2, int file2FromLine, int file2ToLine) {
        setCode1(file1, file1FromLine, file1ToLine);
        setCode2(file2, file2FromLine, file2ToLine);
    }

    private void setCode1(File file1, int file1FromLine, int file1ToLine) {
        Text code1 = readFile(file1);
        iCode1.getChildren().add(code1);
    }

    private void setCode2(File file2, int file2FromLine, int file2ToLine) {
        Text code2 = readFile(file2);
        iCode2.getChildren().add(code2);
    }

    private Text readFile(File file) {
        String result = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int lineNumber = 1;
            String lineFormated = "";

            while (line != null) {
                lineFormated = String.format("%-10d%s", lineNumber, line);
                sb.append(lineFormated);
                sb.append(System.lineSeparator());
                line = br.readLine();
                lineNumber++;
            }

            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Text(result);
    }
}
