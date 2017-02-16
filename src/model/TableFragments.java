package model;

import javafx.beans.property.SimpleStringProperty;

public class TableFragments {

    // Column File1Fragment
    private final SimpleStringProperty rFile1Fragment;
    private String file1FragmentName;
    private int file1FromLine;
    private int file1ToLine;

    // Column File2Fragment
    private final SimpleStringProperty rFile2Fragment;
    private String file2FragmentName;
    private int file2FromLine;
    private int file2ToLine;

    public TableFragments(String file1FragmentName, int file1FromLine, int file1ToLine, String file2FragmentName, int file2FromLine, int file2ToLine) {
        this.file1FragmentName = file1FragmentName;
        this.file1FromLine = file1FromLine;
        this.file1ToLine = file1ToLine;
        this.rFile1Fragment = new SimpleStringProperty();
        updateRFile1Fragment();

        this.file2FragmentName = file2FragmentName;
        this.file2FromLine = file2FromLine;
        this.file2ToLine = file2ToLine;
        this.rFile2Fragment = new SimpleStringProperty();
        updateRFile2Fragment();
    }

    public String getRFile1Fragment() {
        return rFile1Fragment.get();
    }

    private void updateRFile1Fragment() {
        this.rFile1Fragment.set(file1FragmentName + " [" + file1FromLine + "-" + file1ToLine + "]");
    }

    public String getFile1FragmentName() {
        return file1FragmentName;
    }

    public void setFile1FragmentName(String file1FragmentName) {
        this.file1FragmentName = file1FragmentName;
        updateRFile1Fragment();
    }

    public int getFile1FromLine() {
        return file1FromLine;
    }

    public void setFile1FromLine(int file1FromLine) {
        this.file1FromLine = file1FromLine;
        updateRFile1Fragment();
    }

    public int getFile1ToLine() {
        return file1ToLine;
    }

    public void setFile1ToLine(int file1ToLine) {
        this.file1ToLine = file1ToLine;
        updateRFile1Fragment();
    }



    public String getRFile2Fragment() {
        return rFile2Fragment.get();
    }

    private void updateRFile2Fragment() {
        this.rFile2Fragment.set(file2FragmentName + " [" + file2FromLine + "-" + file2ToLine + "]");
    }

    public String getFile2FragmentName() {
        return file2FragmentName;
    }

    public void setFile2FragmentName(String file2FragmentName) {
        this.file2FragmentName = file2FragmentName;
        updateRFile2Fragment();
    }

    public int getFile2FromLine() {
        return file2FromLine;
    }

    public void setFile2FromLine(int file2FromLine) {
        this.file2FromLine = file2FromLine;
        updateRFile2Fragment();
    }

    public int getFile2ToLine() {
        return file2ToLine;
    }

    public void setFile2ToLine(int file2ToLine) {
        this.file2ToLine = file2ToLine;
        updateRFile2Fragment();
    }

}