package controller;

import java.util.ArrayList;

import model.AlgorithmData;
import model.CompareFiles;
import model.tokenization.TokenFile;
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

        ArrayList<TokenFile> projectTokens = Tokenization.tokenProject(algorithmData.getProject());
        ArrayList<TokenFile> baseTokens = Tokenization.tokenProjects(algorithmData.getProjects());

        // TODO: przerobic testCompareFiles z Main.java

        return compareFiles;
    }

    public AlgorithmData getAlgorithmData() {
        return algorithmData;
    }

    public void setAlgorithmData(AlgorithmData algorithmData) {
        this.algorithmData = algorithmData;
    }

}
