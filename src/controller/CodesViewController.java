package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CodesViewController implements Initializable {

    @FXML ScrollPane iScrollPane1;
    @FXML ScrollPane iScrollPane2;

    @FXML TextFlow iCode1;
    @FXML TextFlow iCode2;

    // TODO: usun
    final File FILE_1 = new File("F:\\Desktop\\Game.java");
    final int FROM_LINE_1 = 20;
    final int TO_LINE_1 = 38;
    final File FILE_2 = new File("F:\\Desktop\\Drawing.java");
    final int FROM_LINE_2 = 74;
    final int TO_LINE_2 = 100;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCodes(FILE_1, FROM_LINE_1, TO_LINE_1, FILE_2, FROM_LINE_2, TO_LINE_2);
    }

    // TODO: usun
    @FXML
    private void test(ActionEvent event) {
        scrollToLine(FILE_1, FROM_LINE_1, iScrollPane1, iCode1);
        scrollToLine(FILE_2, FROM_LINE_2, iScrollPane2, iCode2);
    }

    public void setCodes(File file1, int fromLine1, int toLine1, File file2, int fromLine2, int toLine2) {
        setCode1(file1, fromLine1, toLine1);
        setCode2(file2, fromLine2, toLine2);
    }

    private void scrollToLine(File file, int lineNumber, ScrollPane scrollPane, TextFlow textFlow) {
        int fixedLineNumber = lineNumber - 1;   // Zawsze scrolluje o jedną linijkę za dużo

        int totalLines = totalLines(file);
        double position = fixedLineNumber / (double) totalLines;    // Procentowy poziom, na którym jest linijka

        double v = textFlow.getHeight();    // Wysokość całego tekstu
        double t = position * v;            // Wysokość linijki
        double o = scrollPane.getHeight();  // Wysokość pojedyńczego okna, na którym wyświetlany jest tekst
        double s = t / (v-o);               // Wartość przesunięcia scrollbara

        scrollPane.setVvalue(s);
    }

    private void setCode1(File file1, int fromLine1, int toLine1) {
        TextFlow code1 = readFile(file1, fromLine1, toLine1);
        updateFlow(iCode1, code1);
    }

    private void setCode2(File file2, int fromLine2, int toLine2) {
        TextFlow code2 = readFile(file2, fromLine2, toLine2);
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

    private int totalLines(File file) {
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(file));
            lnr.skip(Long.MAX_VALUE);

            int lineNumber = lnr.getLineNumber() + 1;   // Add 1 because line index starts at 0
            lnr.close();
            return lineNumber;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
