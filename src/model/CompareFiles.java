package model;
import java.io.File;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class CompareFiles {

    // Column Project
    private final SimpleStringProperty rProject;

    // Column FileProject
    private final SimpleStringProperty rFileProject;
    private File fileProject;
    private String fileNameProject;
    private int fileLinesProject;

    // Column FileBase
    private final SimpleStringProperty rFileBase;
    private File fileBase;
    private String fileNameBase;
    private int fileLinesBase;

    // Column Matched
    private int matchedValue;
    private final SimpleStringProperty rMatched;

    // Informacje, które będą wyświetlone na kolejnym oknie
    private ArrayList<CompareFragments> compareFragments;

    public CompareFiles(String project, File fileProject, int fileLinesProject, File fileBase, int fileLinesBase, int matchedValue, ArrayList<CompareFragments> compareFragments) {
        this.fileProject = fileProject;
        this.fileNameProject = fileProject.getName();
        this.fileLinesProject = fileLinesProject;
        this.rFileProject = new SimpleStringProperty();
        updateRFileProject();

        this.rProject = new SimpleStringProperty(project);

        this.fileBase = fileBase;
        this.fileNameBase = fileBase.getName();
        this.fileLinesBase = fileLinesBase;
        this.rFileBase = new SimpleStringProperty();
        updateRFileBase();

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



    public String getRFileProject() {
        return rFileProject.get();
    }

    private void updateRFileProject() {
        rFileProject.set(fileNameProject + " (" + fileLinesProject + ")");
    }

    public File getFileProject() {
        return fileProject;
    }

    public void setFileProject(File fileProject) {
        this.fileProject = fileProject;
        this.fileNameProject = fileProject.getName();
        updateRFileProject();
    }

    public String getFileNameProject() {
        return fileNameProject;
    }

    public void setFileNameProject(String value) {
        fileNameProject = value;
        updateRFileProject();
    }

    public int getFileLinesProject() {
        return fileLinesProject;
    }

    public void setFileLinesProject(int value) {
        fileLinesProject = value;
        updateRFileProject();
    }



    public String getRFileBase() {
        return rFileBase.get();
    }

    private void updateRFileBase() {
        rFileBase.set(fileNameBase + " (" + fileLinesBase + ")");
    }

    public File getFileBase() {
        return fileBase;
    }

    public void setFileBase(File fileBase) {
        this.fileBase = fileBase;
        this.fileNameBase = fileBase.getName();
        updateRFileBase();
    }

    public String getFileNameBase() {
        return fileNameBase;
    }

    public void setFileNameBase(String value) {
        fileNameBase = value;
        updateRFileBase();
    }

    public int getFileLinesBase() {
        return fileLinesBase;
    }

    public void setFileLinesBase(int value) {
        fileLinesBase = value;
        updateRFileBase();
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
