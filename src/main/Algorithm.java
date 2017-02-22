package main;

import java.io.File;
import java.util.ArrayList;

import model.AlgorithmData;
import model.CompareFiles;
import model.CompareFragments;
import model.FileMarked;

public class Algorithm extends AlgorithmData {

    public Algorithm() {
        super();
    }

    public ArrayList<CompareFiles> runAlgorithm() {
        if (getProject() == null || getBase() == null) {
            return null;
        }

        // TODO: algorithm
        System.out.println("Algorithm - runAlgorithm - HEJKA");

        return test();
    }

    private ArrayList<CompareFiles> test() {
        FileMarked fileMarked1 = new FileMarked(new File("F:\\Desktop\\Game.java"), 20, 38);
        FileMarked fileMarked2 = new FileMarked(new File("F:\\Desktop\\Drawing.java"), 74, 100);

        ArrayList<CompareFragments> compareFragments1 = new ArrayList<>();
        CompareFragments fragments1 = new CompareFragments(fileMarked1, fileMarked1);
        CompareFragments fragments2 = new CompareFragments(fileMarked1, fileMarked2);
        compareFragments1.add(fragments1);
        compareFragments1.add(fragments2);

        ArrayList<CompareFragments> compareFragments2 = new ArrayList<>();
        CompareFragments fragments3 = new CompareFragments(fileMarked2, fileMarked1);
        CompareFragments fragments4 = new CompareFragments(fileMarked2, fileMarked2);
        compareFragments2.add(fragments3);
        compareFragments2.add(fragments4);

        ArrayList<CompareFiles> compareFiles = new ArrayList<>();
        CompareFiles compareFiles1 = new CompareFiles("MySuperClassController", 365, "Project1", "MainSourceController", 566, 222267, 58, compareFragments1);
        CompareFiles compareFiles2 = new CompareFiles("plik2", 205, "MassiveOnlineUtopia", "plik3", 566, 144187, 95, compareFragments2);
        compareFiles.add(compareFiles1);
        compareFiles.add(compareFiles2);

        return compareFiles;
    }
}
