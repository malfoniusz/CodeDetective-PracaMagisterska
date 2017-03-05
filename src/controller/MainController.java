package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.CompareFiles;

public class MainController implements Initializable {

    @FXML private MenuController menuController;
    @FXML private CompareFilesController compareFilesController;
    @FXML private CompareFragmentsController compareFragmentsController;
    @FXML private CompareCodesController compareCodesController;

    private Algorithm algorithm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        algorithm = new Algorithm();

        menuController.setMainController(this);
        menuController.setAlgorithm(algorithm);
        menuController.updateIItemStart();

        compareFilesController.setCompareFragmentsController(compareFragmentsController);
        compareFilesController.setCompareCodesController(compareCodesController);

        compareFragmentsController.setCompareCodesController(compareCodesController);

        // TODO: delete
        runAlgorithm();
    }

    public void runAlgorithm() {
        ArrayList<CompareFiles> compareFiles = algorithm.runAlgorithm();

        if (compareFiles != null) {
            compareFilesController.setData(compareFiles);
        }
    }

    public MenuController getMenuController() {
        return this.menuController;
    }

    public CompareFilesController getCompareFilesController() {
        return this.compareFilesController;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

}
