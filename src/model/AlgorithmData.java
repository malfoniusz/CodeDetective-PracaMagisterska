package model;

import java.io.File;

public class AlgorithmData {

    private Settings settings;

    private Project project;    // Wybrany projekt do porównania
    private Projects projects;  // Baza projektów, z którymi projekt bedzie porównywany

    public AlgorithmData() {
        settings = new Settings();
        loadSettingsBasePath();
    }

    private void loadSettingsBasePath() {
        String basePath = settings.getBasePath();

        if (basePath == null) {
            this.projects = null;
        }
        else {
            File fileBase = new File(basePath);
            Projects projects = new Projects(fileBase);
            this.setProjects(projects);
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

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = new Projects(projects.getDirectory());
        settings.setBasePath(projects.getDirectory().getAbsolutePath());
    }

}
