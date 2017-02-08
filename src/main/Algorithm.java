package main;

import java.io.File;

import model.Project;
import model.Projects;
import model.Settings;

public class Algorithm {

    private Project project;
    private Projects base;

    public Algorithm() {
        loadSettings();
    }

    public boolean runAlgorithm() {
        if (project == null || base == null) {
            return false;
        }

        // TODO: algorithm
        System.out.println("Algorithm - runAlgorithm - HEJKA");

        return true;
    }

    private void loadSettings() {
        loadSettingsProjectPath();
        loadSettingsBasePath();
    }

    private void loadSettingsProjectPath() {
        String projectPath = Settings.getProjectPath();

        if (projectPath == null) {
            this.project = null;
        }
        else {
            File fileProject = new File(projectPath);
            this.setProject(fileProject);
        }
    }

    private void loadSettingsBasePath() {
        String basePath = Settings.getBasePath();

        if (basePath == null) {
            this.base = null;
        }
        else {
            File fileBase = new File(basePath);
            this.setBase(fileBase);
        }
    }

    public Project getProject() {
        return project;
    }

    public void setProject(File directory) {
        this.project = new Project(directory);
        Settings.setProjectPath(directory.getAbsolutePath());
    }

    public Projects getBase() {
        return base;
    }

    public void setBase(File directory) {
        this.base = new Projects(directory);
        Settings.setBasePath(base.getDirectory().getAbsolutePath());
    }

}
