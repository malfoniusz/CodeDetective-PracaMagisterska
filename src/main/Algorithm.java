package main;

import model.AlgorithmData;

public class Algorithm extends AlgorithmData {

    public Algorithm() {
        super();
    }

    public boolean runAlgorithm() {
        if (getProject() == null || getBase() == null) {
            return false;
        }

        // TODO: algorithm
        System.out.println("Algorithm - runAlgorithm - HEJKA");

        return true;
    }

}
