package model;

import java.io.File;

public class AlgorithmData {

    private Settings settings;

    private Project project;
    private Projects base;

    public AlgorithmData() {
        settings = new Settings();
        loadSettingsBasePath();
    }

    private void loadSettingsBasePath() {
        String basePath = settings.getBasePath();

        if (basePath == null) {
            this.base = null;
        }
        else {
            File fileBase = new File(basePath);
            Projects projects = new Projects(fileBase);
            this.setBase(projects);
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

    public void setProject(Project project) {
        this.project = new Project(project.getDirectory());
    }

    public Projects getBase() {
        return base;
    }

    public void setBase(Projects projects) {
        this.base = new Projects(projects.getDirectory());
        settings.setBasePath(base.getDirectory().getAbsolutePath());
    }

}
