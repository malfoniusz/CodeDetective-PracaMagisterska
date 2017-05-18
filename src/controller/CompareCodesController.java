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

public class CompareCodesController implements Initializable {

    @FXML private ScrollPane iScrollPaneProject;
    @FXML private ScrollPane iScrollPaneBase;

    @FXML private TextFlow iCodeProject;
    @FXML private TextFlow iCodeBase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setCodes(FileMarked fileMarkedProject, FileMarked fileMarkedBase) {
        setCode(iCodeProject, fileMarkedProject);
        setCode(iCodeBase, fileMarkedBase);

        scrollToLine(fileMarkedProject.getFile(), fileMarkedProject.getFromLine(), iScrollPaneProject, iCodeProject);
        scrollToLine(fileMarkedBase.getFile(), fileMarkedBase.getFromLine(), iScrollPaneBase, iCodeBase);
    }

    public void clearCodes() {
        iCodeProject.getChildren().clear();
        iCodeBase.getChildren().clear();
    }

    // UWAGA: okno musi byc zainicializowane przed wywolaniem tej funkcji
    private void scrollToLine(File file, int lineNumber, ScrollPane scrollPane, TextFlow textFlow) {
        int fixedLineNumber = lineNumber - 1;   // Zawsze scrolluje o jedna linijke za duzo

        int totalLines = totalLines(file);
        double position = fixedLineNumber / (double) totalLines;    // Procentowy poziom, na ktorym jest linijka

        double v = textFlow.getHeight();    // Wysokosc calego tekstu
        double t = position * v;            // Wysokosc linijki
        double o = scrollPane.getHeight();  // Wysokosc pojedynczego okna, na ktorym wyswietlany jest tekst
        double s = t / (v-o);               // Wartosc przesuniecia scrollbara

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

        String strFile = read(file);
        String[] splited = strFile.split(System.lineSeparator());

        Text textBefore = new Text(partString(splited, 1, fromLine - 1));

        Text textBetween = new Text(partString(splited, fromLine, toLine));
        textBetween.setFont(Font.font("Verdana", 11));
        textBetween.setFill(Color.CRIMSON);

        Text textAfter = new Text(partString(splited, toLine + 1, splited.length));
        String strAfterTrimmed = textAfter.getText().trim();
        textAfter.setText(strAfterTrimmed);

        return new TextFlow(textBefore, textBetween, textAfter);
    }

    private String partString(String[] strTable, final int fromLine, final int toLine) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = fromLine; i <= toLine; i++) {
    		sb.append(strTable[i-1]);
			sb.append(System.lineSeparator());
    	}

    	return sb.toString();
    }

    private String read(File file) {
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            int lineNumber = 1;

            while (line != null) {
                String lineFormated = String.format("%-10d%s", lineNumber, line);
                sb.append(lineFormated);
                sb.append(System.lineSeparator());

                line = br.readLine();
                lineNumber++;
            }

            return sb.toString();
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
