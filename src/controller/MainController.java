package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Algorithm;

public class MainController implements Initializable {

    @FXML private MenuController menuController;
    @FXML private TableFilesController tableFilesController;

    private Algorithm algorithm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        algorithm = new Algorithm();

        menuController.setDataFromMainController(this);
        menuController.updateIItemStart();
    }

    public void runAlgorithm() {
        algorithm.runAlgorithm();
    }

    public MenuController getMenuController() {
        return this.menuController;
    }

    public TableFilesController getTableFilesController() {
        return this.tableFilesController;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

}
