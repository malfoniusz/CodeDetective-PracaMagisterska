package model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableFiles {

    private final SimpleStringProperty rFile1;
    private String file1Name;
    private int file1Lines;

    private final SimpleStringProperty rFile2;
    private String file2Name;
    private int file2Lines;

    private final SimpleIntegerProperty rLines;

    private int matchedValue;
    private final SimpleStringProperty rMatched;

    public TableFiles(String file1Name, int file1Lines, String file2Name, int file2Lines, int sLines, int matchedValue) {
        this.file1Name = file1Name;
        this.file1Lines = file1Lines;
        this.rFile1 = new SimpleStringProperty(file1Name + " (" + file1Lines + ")");

        this.file2Name = file2Name;
        this.file2Lines = file2Lines;
        this.rFile2 = new SimpleStringProperty(file2Name + " (" + file2Lines + ")");

        this.rLines = new SimpleIntegerProperty(sLines);

        this.matchedValue = matchedValue;
        this.rMatched = new SimpleStringProperty(matchedValue + "%");
    }

    public String getRFile1() {
        return rFile1.get();
    }

    private void updateRFile1() {
        rFile1.set(file1Name + " (" + file1Lines + ")");
    }

    public String getFile1Name() {
        return file1Name;
    }

    public void setFile1Name(String value) {
        file1Name = value;
        updateRFile1();
    }

    public int getFile1Lines() {
        return file1Lines;
    }

    public void setFile1Lines(int value) {
        file1Lines = value;
        updateRFile1();
    }



    public String getRFile2() {
        return rFile2.get();
    }

    private void updateRFile2() {
        rFile2.set(file2Name + " (" + file2Lines + ")");
    }

    public String getFile2Name() {
        return file2Name;
    }

    public void setFile2Name(String value) {
        file2Name = value;
        updateRFile2();
    }

    public int getFile2Lines() {
        return file2Lines;
    }

    public void setFile2Lines(int value) {
        file2Lines = value;
        updateRFile2();
    }



    public int getRLines() {
        return rLines.get();
    }

    public void setRLines(int value) {
        rLines.set(value);
    }



    public String getRMatched() {
        return rMatched.get();
    }

    private void updateRMatched() {
        rMatched.set(matchedValue + "%");;
    }

    public int getMatchedValue() {
        return matchedValue;
    }

    public void setMatchedValue(int value) {
        matchedValue = value;
        updateRMatched();
    }
}
