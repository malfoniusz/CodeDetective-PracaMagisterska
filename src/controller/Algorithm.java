package controller;

import java.io.File;
import java.util.ArrayList;

import model.AlgorithmData;
import model.CompareFiles;
import model.CompareFragments;
import model.FileMarked;
import model.tokenization.TokenFile;
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
        if (algorithmData.getProject() == null || algorithmData.getProjects() == null) {
            return null;
        }

        TokenProject tokenProject = Tokenization.tokenProject(algorithmData.getProject());
        TokenProjects baseProjects = Tokenization.tokenProjects(algorithmData.getProjects());

        ArrayList<CompareFiles> compareFiles = compareMain(tokenProject, baseProjects);
        return compareFiles;
    }

    private ArrayList<CompareFiles> compareMain(TokenProject tokenProject, TokenProjects baseProjects) {
        ArrayList<CompareFiles> compareFiles = new ArrayList<>();

        for (TokenProject baseProject : baseProjects.getTokenProjects()) {
            ArrayList<CompareFiles> compareProjects = compareProjects(tokenProject, baseProject);
            compareFiles.addAll(compareProjects);
        }

        return compareFiles;
    }

    private ArrayList<CompareFiles> compareProjects(TokenProject project, TokenProject baseProject) {
        ArrayList<CompareFiles> compareProjects = new ArrayList<>();

        ArrayList<TokenFile> projectFiles = project.getTokenFiles();
        ArrayList<TokenFile> baseFiles = baseProject.getTokenFiles();

        for (TokenFile baseFile : baseFiles) {
            for (TokenFile projectFile : projectFiles) {
                CompareFiles compareFiles = compareFiles(projectFile, baseFile, baseProject.getName());
                if (compareFiles == null) {
                    continue;
                }
                compareProjects.add(compareFiles);
            }
        }

        return compareProjects;
    }

    private CompareFiles compareFiles(TokenFile projectFile, TokenFile baseFile, String projectName) {
        ArrayList<CompareFragments> compareFragments = algorithm(projectFile, baseFile);
        if (compareFragments.isEmpty()) {
            return null;
        }

        int similarity = calculateSimilarity(compareFragments, projectFile, baseFile);

        CompareFiles compareFiles = new CompareFiles(projectName,
                                                     projectFile.getFile(),
                                                     projectFile.getTotalLines(),
                                                     baseFile.getFile(),
                                                     baseFile.getTotalLines(),
                                                     similarity,
                                                     compareFragments);
        return compareFiles;
    }

    private int calculateSimilarity(ArrayList<CompareFragments> compareFragments, TokenFile projectFile, TokenFile baseFile) {
        if (compareFragments.isEmpty()) {
            return 0;
        }

        // UWAGA pobranie indexu 0
        File file1 = compareFragments.get(0).getFileMarked1().getFile();
        File file2 = compareFragments.get(0).getFileMarked2().getFile();

        int total1 = 0;
        int total2 = 0;
        for (CompareFragments compareFragment : compareFragments) {
            FileMarked fileMarked1 = compareFragment.getFileMarked1();
            FileMarked fileMarked2 = compareFragment.getFileMarked2();

            int difference1 = fileMarked1.getToLine() - fileMarked1.getFromLine();
            total1 += difference1;
            int difference2 = fileMarked2.getToLine() - fileMarked2.getFromLine();
            total2 += difference2;
        }

        int totalTokenLines1 = (projectFile.getFile().equals(file1) ? projectFile.getTotalTokenLines() : baseFile.getTotalTokenLines());
        int totalTokenLines2 = (projectFile.getFile().equals(file2) ? projectFile.getTotalTokenLines() : baseFile.getTotalTokenLines());

        int similarity1 = (total1*100) / totalTokenLines1;
        int similarity2 = (total2*100) / totalTokenLines2;

        int higher = (similarity1 > similarity2 ? similarity1 : similarity2);
        return higher;
    }

    private ArrayList<CompareFragments> algorithm(TokenFile projectFile, TokenFile baseFile) {
        ArrayList<CompareFragments> compareFragments = new ArrayList<>();

        // TODO: algorytm

        FileMarked fileMarked1 = new FileMarked(projectFile.getFile(), 20, 38);
        FileMarked fileMarked2 = new FileMarked(baseFile.getFile(), 15, 32);
        CompareFragments fragments1 = new CompareFragments(fileMarked1, fileMarked2);
        compareFragments.add(fragments1);

        return compareFragments;
    }

    public AlgorithmData getAlgorithmData() {
        return algorithmData;
    }

    public void setAlgorithmData(AlgorithmData algorithmData) {
        this.algorithmData = algorithmData;
    }

}
