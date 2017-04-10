package model;

import javafx.beans.property.SimpleStringProperty;

public class CompareFragments {

    // Column FileFragmentProject
    private final SimpleStringProperty rFileFragmentProject;
    private FileMarked fileMarkedProject;

    // Column FileFragmentBase
    private final SimpleStringProperty rFileFragmentBase;
    private FileMarked fileMarkedBase;

    public CompareFragments(FileMarked fileMarkedProject, FileMarked fileMarkedBase) {
        this.fileMarkedProject = fileMarkedProject;
        this.rFileFragmentProject = new SimpleStringProperty();
        updateRFileFragmentProject();

        this.fileMarkedBase = fileMarkedBase;
        this.rFileFragmentBase = new SimpleStringProperty();
        updateRFileFragmentBase();
    }

    public String getRFileFragmentProject() {
        return rFileFragmentProject.get();
    }

    private void updateRFileFragmentProject() {
        this.rFileFragmentProject.set(fileMarkedProject.getFile().getName() + " [" + fileMarkedProject.getFromLine() + "-" + fileMarkedProject.getToLine() + "]");
    }

    public FileMarked getFileMarkedProject() {
        return fileMarkedProject;
    }

    public void setFileMarkedProject(FileMarked fileMarkedProject) {
        this.fileMarkedProject = fileMarkedProject;
        updateRFileFragmentProject();
    }


    public String getRFileFragmentBase() {
        return rFileFragmentBase.get();
    }

    private void updateRFileFragmentBase() {
        this.rFileFragmentBase.set(fileMarkedBase.getFile().getName() + " [" + fileMarkedBase.getFromLine() + "-" + fileMarkedBase.getToLine() + "]");
    }

    public FileMarked getFileMarkedBase() {
        return fileMarkedBase;
    }

    public void setFileMarkedBase(FileMarked fileMarkedBase) {
        this.fileMarkedBase = fileMarkedBase;
        updateRFileFragmentBase();
    }

}
