package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.FileMarked;

public class CodesViewController implements Initializable {

    @FXML private ScrollPane iScrollPane1;
    @FXML private ScrollPane iScrollPane2;

    @FXML private TextFlow iCode1;
    @FXML private TextFlow iCode2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setCodes(FileMarked fileMarked1, FileMarked fileMarked2) {
        setCode(iCode1, fileMarked1);
        setCode(iCode2, fileMarked2);

        scrollToLine(fileMarked1.getFile(), fileMarked1.getFromLine(), iScrollPane1, iCode1);
        scrollToLine(fileMarked2.getFile(), fileMarked2.getFromLine(), iScrollPane2, iCode2);
    }

    public void clearCodes() {
        iCode1.getChildren().clear();
        iCode2.getChildren().clear();
    }

    // UWAGA: okno musi być zainicializowane przed wywołaniem tej funkcji
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

    private void setCode(TextFlow textFlow, FileMarked fileMarked) {
        TextFlow code = readFile(fileMarked);
        textFlow.getChildren().setAll(code);

        // Powiadomienie programu o zmianie rozmiaru okna
        textFlow.autosize();
    }

    private TextFlow readFile(FileMarked fileMarked) {
        File file = fileMarked.getFile();
        int fromLine = fileMarked.getFromLine();
        int toLine = fileMarked.getToLine();
        
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
