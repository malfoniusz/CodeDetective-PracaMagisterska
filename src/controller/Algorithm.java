package controller;

import java.io.File;
import java.util.ArrayList;

import model.AlgorithmData;
import model.CompareFiles;
import model.CompareFragments;
import model.FileMarked;
import model.Project;
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

        // TODO: usun
        ArrayList<File> files = new ArrayList<>();
        //files.add(new File("F:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\Game.java"));
        files.add(new File("F:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\Drawing.java"));
        File directory = new File("F:\\Documents\\_Praca magisterska\\ProjektyDoTestow");
        Project project = new Project(directory, files);
        Tokenization.tokenProject(project);

        // TODO: uncomment
        // ArrayList<TokenFile> projectTokens = Tokenization.tokenProject(algorithmData.getProject());
        // ArrayList<TokenFile> baseTokens = Tokenization.tokenProjects(algorithmData.getProjects());

        return test();
    }

    private ArrayList<CompareFiles> test() {
        // TODO: usun
        File file1 = new File("F:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\Tic-Tac-Toe\\src\\Drawing.java");
        File file2 = new File("F:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\Tic-Tac-Toe\\src\\Game.java");

        FileMarked fileMarked1a = new FileMarked(file1, 20, 38);
        FileMarked fileMarked1b = new FileMarked(file1, 30, 40);
        FileMarked fileMarked2a = new FileMarked(file2, 15, 32);
        FileMarked fileMarked2b = new FileMarked(file2, 74, 100);

        ArrayList<CompareFragments> compareFragments1 = new ArrayList<>();
        CompareFragments fragments1 = new CompareFragments(fileMarked1a, fileMarked2a);
        CompareFragments fragments2 = new CompareFragments(fileMarked1b, fileMarked2b);
        compareFragments1.add(fragments1);
        compareFragments1.add(fragments2);

        ArrayList<CompareFragments> compareFragments2 = new ArrayList<>();
        CompareFragments fragments3 = new CompareFragments(fileMarked2a, fileMarked1a);
        CompareFragments fragments4 = new CompareFragments(fileMarked2b, fileMarked1b);
        compareFragments2.add(fragments3);
        compareFragments2.add(fragments4);

        ArrayList<CompareFiles> compareFiles = new ArrayList<>();
        CompareFiles compareFiles1 = new CompareFiles("Project1", file1, 365, file2, 566, 222267, 58, compareFragments1);
        CompareFiles compareFiles2 = new CompareFiles("MassiveOnlineUtopia", new File("myPath/plik2"), 205, new File("myPath/plik3"), 566, 144187, 95, compareFragments2);
        compareFiles.add(compareFiles1);
        compareFiles.add(compareFiles2);

        return compareFiles;
    }

    public AlgorithmData getAlgorithmData() {
        return algorithmData;
    }

    public void setAlgorithmData(AlgorithmData algorithmData) {
        this.algorithmData = algorithmData;
    }

}
