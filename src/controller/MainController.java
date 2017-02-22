package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Algorithm;
import model.TableFiles;

public class MainController implements Initializable {

    @FXML private MenuController menuController;
    @FXML private TableFilesController tableFilesController;
    @FXML private TableFragmentsController tableFragmentsController;
    @FXML private CodesViewController codesViewController;

    private Algorithm algorithm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        algorithm = new Algorithm();

        menuController.setMainController(this);
        menuController.setAlgorithm(algorithm);
        menuController.updateIItemStart();

        tableFilesController.setTableFragmentsController(tableFragmentsController);
        tableFilesController.setCodesViewController(codesViewController);

        tableFragmentsController.setCodesViewController(codesViewController);
    }

    public void runAlgorithm() {
        ArrayList<TableFiles> tableFiles = algorithm.runAlgorithm();

        if (tableFiles != null) {
            tableFilesController.setData(tableFiles);
        }
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
