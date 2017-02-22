package model;

import java.io.File;

import javafx.beans.property.SimpleStringProperty;

public class TableFragments {

    // Column File1Fragment
    private final SimpleStringProperty rFileFragment1;
    private File file1;
    private int fromLine1;
    private int toLine1;

    // Column File2Fragment
    private final SimpleStringProperty rFileFragment2;
    private File file2;
    private int fromLine2;
    private int toLine2;

    public TableFragments(File file1, int fromLine1, int toLine1, File file2, int fromLine2, int toLine2) {
        this.file1 = file1;
        this.fromLine1 = fromLine1;
        this.toLine1 = toLine1;
        this.rFileFragment1 = new SimpleStringProperty();
        updateRFileFragment1();

        this.file2 = file2;
        this.fromLine2 = fromLine2;
        this.toLine2 = toLine2;
        this.rFileFragment2 = new SimpleStringProperty();
        updateRFileFragment2();
    }

    public String getRFileFragment1() {
        return rFileFragment1.get();
    }

    private void updateRFileFragment1() {
        this.rFileFragment1.set(file1.getName() + " [" + fromLine1 + "-" + toLine1 + "]");
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public int getFromLine1() {
        return fromLine1;
    }

    public void setFromLine1(int fromLine1) {
        this.fromLine1 = fromLine1;
        updateRFileFragment1();
    }

    public int getToLine1() {
        return toLine1;
    }

    public void setToLine1(int toLine1) {
        this.toLine1 = toLine1;
        updateRFileFragment1();
    }



    public String getRFileFragment2() {
        return rFileFragment2.get();
    }

    private void updateRFileFragment2() {
        this.rFileFragment2.set(file2.getName() + " [" + fromLine2 + "-" + toLine2 + "]");
    }

    public File getFile2() {
        return file2;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }

    public int getFromLine2() {
        return fromLine2;
    }

    public void setFromLine2(int fromLine2) {
        this.fromLine2 = fromLine2;
        updateRFileFragment2();
    }

    public int getToLine2() {
        return toLine2;
    }

    public void setToLine2(int toLine2) {
        this.toLine2 = toLine2;
        updateRFileFragment2();
    }

}
