package model;
import java.io.File;
import java.util.ArrayList;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CompareFiles {

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

    // Column Project
    private final SimpleStringProperty rBaseName;

    // Longest match
    private final SimpleIntegerProperty rLongestMatch;

    // Column Matched
    private final SimpleFloatProperty rSimilarity;  // <0, 100>

    // Informacje, które będą wyświetlone na kolejnym oknie
    private ArrayList<CompareFragments> compareFragments;

    public CompareFiles(File projectDir, File fileProject, int fileProjectLines, File baseDir, File fileBase, int fileBaseLines, int longestMatch, float similarity, ArrayList<CompareFragments> compareFragments) {
        this.rBaseName = new SimpleStringProperty(baseDir.getName());

        this.fileProject = fileProject;
        this.fileProjectLines = fileProjectLines;
        this.fileProjectShortPath = fileShortProjectPath(fileProject, projectDir);
        this.rFileProject = new SimpleStringProperty();
        updateRFileProject();

        this.fileBase = fileBase;
        this.fileBaseLines = fileBaseLines;
        this.fileBaseShortPath = fileShortBasePath(fileBase, baseDir);
        this.rFileBase = new SimpleStringProperty();
        updateRFileBase();

        this.rLongestMatch = new SimpleIntegerProperty(longestMatch);

        similarity = Math.round(similarity*10000) / (float) 100; // Zaokraglenie do dwoch miejsc po przecinku i zamiana na procenty
        this.rSimilarity = new SimpleFloatProperty(similarity);

        this.compareFragments = compareFragments;
    }

    private String fileShortProjectPath(File file, File dir) {
        String filePath = file.getPath();
        String dirPath = dir.getPath();

        String shortPath = "." + filePath.replace(dirPath, "");
        return shortPath;
    }

    private String fileShortBasePath(File file, File dir) {
        String filePath = file.getPath();
        String dirPath = dir.getPath();
        String dirName = dir.getName();

        String shortPath = ".\\" + filePath.replace(dirPath, dirName);
        return shortPath;
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



    public String getRBaseName() {
        return rBaseName.get();
    }

    public void setRBaseName(String value) {
        rBaseName.set(value);
    }



    public int getRLongestMatch() {
        return rLongestMatch.get();
    }

    public void setRLongestMatch(int value) {
        rLongestMatch.set(value);
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.rFileProject.getValue() + " ");
        sb.append(this.rFileBase.getValue() + " ");
        sb.append(this.rBaseName.getValue() + " ");
        sb.append(this.rLongestMatch.getValue() + " ");
        sb.append(this.rSimilarity.getValue() + " ");
        sb.append(this.fileProjectShortPath + " ");
        sb.append(this.fileBaseShortPath + " ");

        for (CompareFragments compareFragment : this.compareFragments) {
            sb.append(System.lineSeparator() + compareFragment.toString());
        }

        return sb.toString();
    }

}
