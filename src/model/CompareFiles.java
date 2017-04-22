package model;
import java.io.File;
import java.util.ArrayList;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class CompareFiles {

    // Column Project
    private final SimpleStringProperty rBaseName;

    // Column FileProject
    private final SimpleStringProperty rFileProject;
    private File fileProject;
    private int fileProjectLines;
    private String fileProjectShortPath;

    // Column FileBase
    private final SimpleStringProperty rFileBase;
    private File fileBase;
    private int fileBaseLines;
    private String fileBaseShortPath;

    // Column Matched
    private final SimpleFloatProperty rSimilarity;

    // Informacje, które będą wyświetlone na kolejnym oknie
    private ArrayList<CompareFragments> compareFragments;

    public CompareFiles(String projectName, File fileProject, int fileProjectLines, String baseName, File fileBase, int fileBaseLines, float similarity, ArrayList<CompareFragments> compareFragments) {
        this.rBaseName = new SimpleStringProperty(baseName);

        this.fileProject = fileProject;
        this.fileProjectLines = fileProjectLines;
        this.fileProjectShortPath = fileShortPath(fileProject, projectName);
        this.rFileProject = new SimpleStringProperty();
        updateRFileProject();

        this.fileBase = fileBase;
        this.fileBaseLines = fileBaseLines;
        this.fileBaseShortPath = fileShortPath(fileBase, baseName);
        this.rFileBase = new SimpleStringProperty();
        updateRFileBase();

        // Zaokraglenie do dwoch miejsc po przecinku i zamiana na procenty
        similarity = Math.round(similarity*10000) / (float) 100;
        this.rSimilarity = new SimpleFloatProperty(similarity);

        this.compareFragments = compareFragments;
    }

    private String fileShortPath(File file, String folderName) {
        String path = file.getPath();
        int beginIndexBase = path.indexOf(folderName);
        int endIndexBase = path.indexOf(file.getName());
        String shortPath = ".\\" + path.substring(beginIndexBase, endIndexBase);
        return shortPath;
    }


    public String getRBaseName() {
        return rBaseName.get();
    }

    public void setRBaseName(String value) {
        rBaseName.set(value);
    }



    public String getRFileProject() {
        return rFileProject.get();
    }

    private void updateRFileProject() {
        rFileProject.set(fileProject.getName() + " (" + fileProjectLines + ")");
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

    public String getFileProjectShortPath() {
        return fileProjectShortPath;
    }

    public void setFileProjectShortPath(String fileProjectShortPath) {
        this.fileProjectShortPath = fileProjectShortPath;
    }



    public String getRFileBase() {
        return rFileBase.get();
    }

    private void updateRFileBase() {
        rFileBase.set(fileBase.getName() + " (" + fileBaseLines + ")");
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

    public String getFileBaseShortPath() {
        return fileBaseShortPath;
    }

    public void setFileBaseShortPath(String fileBaseShortPath) {
        this.fileBaseShortPath = fileBaseShortPath;
    }



    public float getRSimilarity() {
        return rSimilarity.get();
    }

    public void setRSimilarity(int value) {
        rSimilarity.set(value);
    }



    public ArrayList<CompareFragments> getCompareFragments() {
        return compareFragments;
    }

    public void setCompareFragments(ArrayList<CompareFragments> compareFragments) {
        this.compareFragments = compareFragments;
    }
}
