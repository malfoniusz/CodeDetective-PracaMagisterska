package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CodesViewController implements Initializable {

    @FXML TextFlow iCode1;
    @FXML TextFlow iCode2;

    final File file1 = new File("F:\\Desktop\\Game.java");
    final File file2 = new File("F:\\Desktop\\Drawing.java");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCodes(file1, 20, 38, file2, 74, 100);
    }

    public void setCodes(File file1, int file1FromLine, int file1ToLine, File file2, int file2FromLine, int file2ToLine) {
        setCode1(file1, file1FromLine, file1ToLine);
        setCode2(file2, file2FromLine, file2ToLine);
    }

    private void setCode1(File file1, int file1FromLine, int file1ToLine) {
        TextFlow code1 = readFile(file1, file1FromLine, file1ToLine);
        updateFlow(iCode1, code1);
    }

    private void setCode2(File file2, int file2FromLine, int file2ToLine) {
        TextFlow code2 = readFile(file2, file2FromLine, file2ToLine);
        updateFlow(iCode2, code2);
    }

    private TextFlow readFile(File file, int fromLine, int toLine) {
        Text textBefore = read(file, 1, fromLine - 1);

        Text textBetween = read(file, fromLine, toLine);
        textBetween.setFont(Font.font("Verdana", 11));
        textBetween.setFill(Color.CRIMSON);

        Text textAfter = read(file, toLine + 1, -1);
        String strAfterTrimmed = textAfter.getText().trim();
        textAfter.setText(strAfterTrimmed);

        return new TextFlow(textBefore, textBetween, textAfter);
    }

    private Text read(File file, final int fromLine, final int toLine) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            int lineNumber = 1;

            while (line != null) {
                if (toLine > 0 && lineNumber > toLine) {
                    break;
                }
                else if (lineNumber >= fromLine) {
                    String lineFormated = String.format("%-10d%s", lineNumber, line);
                    sb.append(lineFormated);
                    sb.append(System.lineSeparator());
                }

                line = br.readLine();
                lineNumber++;
            }

            return new Text(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void updateFlow(TextFlow flow, TextFlow newData) {
        flow.getChildren().clear();
        flow.getChildren().addAll(newData);
    }
}
