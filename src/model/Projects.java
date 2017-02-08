package model;

import java.io.File;
import java.util.ArrayList;

public class Projects {

    private File directory;
    private ArrayList<Project> projects;

    public Projects(File directory) {
        this.directory = directory;
        this.projects = createProjects(directory);
    }

    private ArrayList<Project> createProjects(File directory) {
        ArrayList<Project> projects = new ArrayList<Project>();

        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                Project project = new Project(file);
                projects.add(project);
            }
        }

        return projects;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        String str = directory.getPath() + "\n";
        for (Project project : projects) {
            str += project.toString();
        }
        return str;
    }
}
