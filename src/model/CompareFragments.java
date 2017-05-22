package model;

import java.io.File;

import javafx.beans.property.SimpleIntegerProperty;

public class CompareFragments {

    // Column Project
    private File projectFile;
    private final SimpleIntegerProperty rProjectFrom;
    private final SimpleIntegerProperty rProjectTo;
    private final SimpleIntegerProperty rProjectLength;

    // Column Base
    private File baseFile;
    private final SimpleIntegerProperty rBaseFrom;
    private final SimpleIntegerProperty rBaseTo;
    private final SimpleIntegerProperty rBaseLength;

    public CompareFragments(FileMarked fileMarkedProject, FileMarked fileMarkedBase) {
        this.projectFile = fileMarkedProject.getFile();
        this.rProjectFrom = new SimpleIntegerProperty(fileMarkedProject.getFromLine());
        this.rProjectTo = new SimpleIntegerProperty(fileMarkedProject.getToLine());
        this.rProjectLength = new SimpleIntegerProperty(fileMarkedProject.getLength());

        this.baseFile = fileMarkedBase.getFile();
        this.rBaseFrom = new SimpleIntegerProperty(fileMarkedBase.getFromLine());
        this.rBaseTo = new SimpleIntegerProperty(fileMarkedBase.getToLine());
        this.rBaseLength = new SimpleIntegerProperty(fileMarkedBase.getLength());
    }

    public File getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(File projectFile) {
        this.projectFile = projectFile;
    }

    public File getBaseFile() {
        return baseFile;
    }

    public void setBaseFile(File baseFile) {
        this.baseFile = baseFile;
    }

    public int getRProjectFrom() {
        return rProjectFrom.get();
    }

    public int getRProjectTo() {
        return rProjectTo.get();
    }

    public int getRProjectLength() {
        return rProjectLength.get();
    }

    public int getRBaseFrom() {
        return rBaseFrom.get();
    }

    public int getRBaseTo() {
        return rBaseTo.get();
    }

    public int getRBaseLength() {
        return rBaseLength.get();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + rProjectFrom.get() + "-" + rProjectTo.get() + "] ");
        sb.append("(" + rProjectLength.get() + ")");

        sb.append("[" + rBaseFrom.get() + "-" + rBaseTo.get() + "] ");
        sb.append("(" + rBaseLength.get() + ")");

        return sb.toString();
    }

}
