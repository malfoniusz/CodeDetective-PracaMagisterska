package main;

import java.io.File;
import java.util.ArrayList;

import model.AlgorithmData;
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
        File file1 = new File("F:\\Desktop\\Game.java");
        int fromLine1 = 20;
        int toLine1 = 38;
        File file2 = new File("F:\\Desktop\\Drawing.java");
        int fromLine2 = 74;
        int toLine2 = 100;

        ArrayList<TableFragments> tableFragments1 = new ArrayList<>();
        TableFragments fragments1 = new TableFragments(file1, fromLine1, toLine1, file1, fromLine1, toLine1);
        TableFragments fragments2 = new TableFragments(file1, fromLine1, toLine1, file2, fromLine2, toLine2);
        tableFragments1.add(fragments1);
        tableFragments1.add(fragments2);

        ArrayList<TableFragments> tableFragments2 = new ArrayList<>();
        TableFragments fragments3 = new TableFragments(file2, fromLine2, toLine2, file1, fromLine1, toLine1);
        TableFragments fragments4 = new TableFragments(file2, fromLine2, toLine2, file2, fromLine2, toLine2);
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
