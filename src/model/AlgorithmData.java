package model;

import java.io.File;

public class AlgorithmData {

    private Settings settings;

    private Project project;
    private Projects base;

    public AlgorithmData() {
        settings = new Settings();
        loadSettings();
    }

    private void loadSettings() {
        loadSettingsProjectPath();
        loadSettingsBasePath();
    }

    private void loadSettingsProjectPath() {
        String projectPath = settings.getProjectPath();

        if (projectPath == null) {
            this.project = null;
        }
        else {
            File fileProject = new File(projectPath);
            this.setProject(fileProject);
        }
    }

    private void loadSettingsBasePath() {
        String basePath = settings.getBasePath();

        if (basePath == null) {
            this.base = null;
        }
        else {
            File fileBase = new File(basePath);
            this.setBase(fileBase);
        }
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(File directory) {
        this.project = new Project(directory);
        settings.setProjectPath(directory.getAbsolutePath());
    }

    public Projects getBase() {
        return base;
    }

    public void setBase(File directory) {
        this.base = new Projects(directory);
        settings.setBasePath(base.getDirectory().getAbsolutePath());
    }

}
