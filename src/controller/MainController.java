package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Algorithm;
import model.Files;

public class MainController implements Initializable {

    @FXML private MenuController menuController;
    @FXML private FilesController filesController;
    @FXML private TableFragmentsController tableFragmentsController;
    @FXML private CodesViewController codesViewController;

    private Algorithm algorithm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        algorithm = new Algorithm();

        menuController.setMainController(this);
        menuController.setAlgorithm(algorithm);
        menuController.updateIItemStart();

        filesController.setTableFragmentsController(tableFragmentsController);
        filesController.setCodesViewController(codesViewController);

        tableFragmentsController.setCodesViewController(codesViewController);
    }

    public void runAlgorithm() {
        ArrayList<Files> files = algorithm.runAlgorithm();

        if (files != null) {
            filesController.setData(files);
        }
    }

    public MenuController getMenuController() {
        return this.menuController;
    }

    public FilesController getFilesController() {
        return this.filesController;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

}
