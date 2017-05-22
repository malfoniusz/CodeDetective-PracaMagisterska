package model;

import javafx.beans.property.SimpleIntegerProperty;

public class CompareFragments {

    // Column Project
    private final SimpleIntegerProperty rProjectFrom;
    private final SimpleIntegerProperty rProjectTo;
    private final SimpleIntegerProperty rProjectLength;
    private FileMarked fileMarkedProject;

    // Column Base
    private final SimpleIntegerProperty rBaseFrom;
    private final SimpleIntegerProperty rBaseTo;
    private final SimpleIntegerProperty rBaseLength;
    private FileMarked fileMarkedBase;

    public CompareFragments(FileMarked fileMarkedProject, FileMarked fileMarkedBase) {
        this.fileMarkedProject = fileMarkedProject;
        this.rProjectFrom = new SimpleIntegerProperty(fileMarkedProject.getFromLine());
        this.rProjectTo = new SimpleIntegerProperty(fileMarkedProject.getToLine());
        this.rProjectLength = new SimpleIntegerProperty(fileMarkedProject.getLength());

        this.fileMarkedBase = fileMarkedBase;
        this.rBaseFrom = new SimpleIntegerProperty(fileMarkedBase.getFromLine());
        this.rBaseTo = new SimpleIntegerProperty(fileMarkedBase.getToLine());
        this.rBaseLength = new SimpleIntegerProperty(fileMarkedBase.getLength());
    }

    public FileMarked getFileMarkedProject() {
        return fileMarkedProject;
    }

    public void setFileMarkedProject(FileMarked fileMarkedProject) {
        this.fileMarkedProject = fileMarkedProject;
    }

    public FileMarked getFileMarkedBase() {
        return fileMarkedBase;
    }

    public void setFileMarkedBase(FileMarked fileMarkedBase) {
        this.fileMarkedBase = fileMarkedBase;
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
