package main;

import java.io.File;
import java.util.ArrayList;

import model.AlgorithmData;
import model.FileMarked;
import model.TableFiles;
import model.TableFragments;

public class Algorithm extends AlgorithmData {

    public Algorithm() {
        super();
    }

    public ArrayList<TableFiles> runAlgorithm() {
        if (getProject() == null || getBase() == null) {
            return null;
        }

        // TODO: algorithm
        System.out.println("Algorithm - runAlgorithm - HEJKA");

        return test();
    }

    private ArrayList<TableFiles> test() {
        FileMarked fileMarked1 = new FileMarked(new File("F:\\Desktop\\Game.java"), 20, 38);
        FileMarked fileMarked2 = new FileMarked(new File("F:\\Desktop\\Drawing.java"), 74, 100);

        ArrayList<TableFragments> tableFragments1 = new ArrayList<>();
        TableFragments fragments1 = new TableFragments(fileMarked1, fileMarked1);
        TableFragments fragments2 = new TableFragments(fileMarked1, fileMarked2);
        tableFragments1.add(fragments1);
        tableFragments1.add(fragments2);

        ArrayList<TableFragments> tableFragments2 = new ArrayList<>();
        TableFragments fragments3 = new TableFragments(fileMarked2, fileMarked1);
        TableFragments fragments4 = new TableFragments(fileMarked2, fileMarked2);
        tableFragments2.add(fragments3);
        tableFragments2.add(fragments4);

        ArrayList<TableFiles> tableFiles = new ArrayList<>();
        TableFiles files1 = new TableFiles("MySuperClassController", 365, "Project1", "MainSourceController", 566, 222267, 58, tableFragments1);
        TableFiles files2 = new TableFiles("plik2", 205, "MassiveOnlineUtopia", "plik3", 566, 144187, 95, tableFragments2);
        tableFiles.add(files1);
        tableFiles.add(files2);

        return tableFiles;
    }
}
