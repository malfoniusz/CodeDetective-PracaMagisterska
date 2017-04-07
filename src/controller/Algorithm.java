package controller;

import java.util.ArrayList;

import model.AlgorithmData;
import model.CompareFiles;
import model.tokenization.TokenProject;
import model.tokenization.TokenProjects;
import staticc.Tokenization;

public class Algorithm {

    private AlgorithmData algorithmData;

    public Algorithm() {
        super();
        algorithmData = new AlgorithmData();
    }

    public ArrayList<CompareFiles> runAlgorithm() {
        ArrayList<CompareFiles> compareFiles = new ArrayList<>();

        if (algorithmData.getProject() == null || algorithmData.getProjects() == null) {
            return null;
        }

        TokenProject tokenProject = Tokenization.tokenProject(algorithmData.getProject());
        TokenProjects tokenProjects = Tokenization.tokenProjects(algorithmData.getProjects());

        return compareFiles;
    }

    public AlgorithmData getAlgorithmData() {
        return algorithmData;
    }

    public void setAlgorithmData(AlgorithmData algorithmData) {
        this.algorithmData = algorithmData;
    }

}
