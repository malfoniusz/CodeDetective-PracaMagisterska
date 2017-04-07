package model;
import java.io.File;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class CompareFiles {

    // Column Project
    private final SimpleStringProperty rProject;

    // Column File1
    private final SimpleStringProperty rFile1;
    private File file1;
    private String fileName1;
    private int fileLines1;

    // Column File2
    private final SimpleStringProperty rFile2;
    private File file2;
    private String fileName2;
    private int fileLines2;

    // Column Matched
    private int matchedValue;
    private final SimpleStringProperty rMatched;

    // Informacje, które będą wyświetlone na kolejnym oknie
    private ArrayList<CompareFragments> compareFragments;

    public CompareFiles(String project, File file1, int fileLines1, File file2, int fileLines2, int matchedValue, ArrayList<CompareFragments> compareFragments) {
        this.file1 = file1;
        this.fileName1 = file1.getName();
        this.fileLines1 = fileLines1;
        this.rFile1 = new SimpleStringProperty();
        updateRFile1();

        this.rProject = new SimpleStringProperty(project);

        this.file2 = file2;
        this.fileName2 = file2.getName();
        this.fileLines2 = fileLines2;
        this.rFile2 = new SimpleStringProperty();
        updateRFile2();

        this.matchedValue = matchedValue;
        this.rMatched = new SimpleStringProperty();
        updateRMatched();

        this.compareFragments = compareFragments;
    }

    public String getRProject() {
        return rProject.get();
    }

    public void setRProject(String value) {
        rProject.set(value);
    }



    public String getRFile1() {
        return rFile1.get();
    }

    private void updateRFile1() {
        rFile1.set(fileName1 + " (" + fileLines1 + ")");
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
        this.fileName1 = file1.getName();
        updateRFile1();
    }

    public String getFileName1() {
        return fileName1;
    }

    public void setFileName1(String value) {
        fileName1 = value;
        updateRFile1();
    }

    public int getFileLines1() {
        return fileLines1;
    }

    public void setFileLines1(int value) {
        fileLines1 = value;
        updateRFile1();
    }



    public String getRFile2() {
        return rFile2.get();
    }

    private void updateRFile2() {
        rFile2.set(fileName2 + " (" + fileLines2 + ")");
    }

    public File getFile2() {
        return file2;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
        this.fileName2 = file2.getName();
        updateRFile2();
    }

    public String getFileName2() {
        return fileName2;
    }

    public void setFileName2(String value) {
        fileName2 = value;
        updateRFile2();
    }

    public int getFileLines2() {
        return fileLines2;
    }

    public void setFileLines2(int value) {
        fileLines2 = value;
        updateRFile2();
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



    public ArrayList<CompareFragments> getCompareFragments() {
        return compareFragments;
    }

    public void setCompareFragments(ArrayList<CompareFragments> compareFragments) {
        this.compareFragments = compareFragments;
    }
}
