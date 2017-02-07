package main;

import java.util.ArrayList;

import model.Project;

public class Algorithm {

    private Project chosenProject;
    private ArrayList<Project> chosenBase;

    public Algorithm(Project chosenProject, ArrayList<Project> chosenBase) {
        this.chosenProject = chosenProject;
        this.chosenBase = chosenBase;
    }

    public void runAlgorithm() {
        // TODO: algorithm
    }

    public Project getChosenProject() {
        return chosenProject;
    }

    public void setChosenProject(Project chosenProject) {
        this.chosenProject = chosenProject;
    }

    public ArrayList<Project> getChosenBase() {
        return chosenBase;
    }

    public void setChosenBase(ArrayList<Project> chosenBase) {
        this.chosenBase = chosenBase;
    }

}
