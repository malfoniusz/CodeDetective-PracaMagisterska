package model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableFiles {

    private final SimpleStringProperty rFile1;
    private final SimpleStringProperty rFile2;
    private final SimpleIntegerProperty rLines;
    private final SimpleStringProperty rMatched;

    public TableFiles(String sFile1, String sFile2, int sLines, String sMatched) {
        this.rFile1 = new SimpleStringProperty(sFile1);
        this.rFile2 = new SimpleStringProperty(sFile2);
        this.rLines = new SimpleIntegerProperty(sLines);
        this.rMatched = new SimpleStringProperty(sMatched);
    }

    public String getRFile1() {
        return rFile1.get();
    }

    public void setRFile1(String value) {
        rFile1.set(value);
    }

    public String getRFile2() {
        return rFile2.get();
    }

    public void setRFile2(String value) {
        rFile2.set(value);
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

    public void setRMatched(String value) {
        rMatched.set(value);
    }

}
