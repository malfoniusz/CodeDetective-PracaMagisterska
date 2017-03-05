package model;

import java.io.File;

import staticc.Settings;

public class AlgorithmData {

    private Project project;    // Wybrany projekt do porównania
    private Projects projects;  // Baza projektów, z którymi projekt bedzie porównywany

    public AlgorithmData() {
        loadSettingsBasePath();
    }

    private void loadSettingsBasePath() {
        String basePath = Settings.getBasePath();

        if (basePath == null) {
            this.projects = null;
        }
        else {
            File fileBase = new File(basePath);
            Projects projects = new Projects(fileBase);
            this.setProjects(projects);
        }
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
        Settings.setBasePath(projects.getDirectory().getAbsolutePath());
    }

}
