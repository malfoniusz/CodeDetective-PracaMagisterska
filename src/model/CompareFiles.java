package model;
import java.io.File;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class CompareFiles {

    // Column Project
    private final SimpleStringProperty rBaseName;

    // Column FileProject
    private final SimpleStringProperty rFileProject;
    private String projectName;
    private File fileProject;
    private String fileProjectShortPath;
    private int fileProjectLines;

    // Column FileBase
    private final SimpleStringProperty rFileBase;
    private String baseName;
    private File fileBase;
    private int fileBaseLines;

    // Column Matched
    private int matchedValue;
    private final SimpleStringProperty rMatched;

    // Informacje, które będą wyświetlone na kolejnym oknie
    private ArrayList<CompareFragments> compareFragments;

    public CompareFiles(String projectName, File fileProject, int fileProjectLines, String baseName, File fileBase, int fileBaseLines, int matchedValue, ArrayList<CompareFragments> compareFragments) {
        this.rBaseName = new SimpleStringProperty(baseName);

        this.projectName = projectName;
        this.fileProject = fileProject;
        this.fileProjectLines = fileProjectLines;
        this.rFileProject = new SimpleStringProperty();
        updateRFileProject();

        this.baseName = baseName;
        this.fileBase = fileBase;
        this.fileBaseLines = fileBaseLines;
        this.rFileBase = new SimpleStringProperty();
        updateRFileBase();

        this.matchedValue = matchedValue;
        this.rMatched = new SimpleStringProperty();
        updateRMatched();

        this.compareFragments = compareFragments;
    }

    public String getRBaseName() {
        return rBaseName.get();
    }

    public void setRBaseName(String value) {
        rBaseName.set(value);
        this.baseName = value;
    }



    public String getRFileProject() {
        return rFileProject.get();
    }

    private void updateRFileProject() {
        rFileProject.set(fileProject.getName() + " (" + fileProjectLines + ")");
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public File getFileProject() {
        return fileProject;
    }

    public void setFileProject(File fileProject) {
        this.fileProject = fileProject;
        updateRFileProject();
    }

    public int getFileProjectLines() {
        return fileProjectLines;
    }

    public void setFileProjectLines(int value) {
        fileProjectLines = value;
        updateRFileProject();
    }



    public String getRFileBase() {
        return rFileBase.get();
    }

    private void updateRFileBase() {
        rFileBase.set(fileBase.getName() + " (" + fileBaseLines + ")");
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
        rBaseName.set(baseName);
    }

    public File getFileBase() {
        return fileBase;
    }

    public void setFileBase(File fileBase) {
        this.fileBase = fileBase;
        updateRFileBase();
    }

    public int getFileBaseLines() {
        return fileBaseLines;
    }

    public void setFileBaseLines(int value) {
        fileBaseLines = value;
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
