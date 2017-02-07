package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Algorithm;
import model.Project;

public class MainController implements Initializable {

    @FXML private MenuController menuController;
    @FXML private TableFilesController tableFilesController;

    private Algorithm algorithm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuController.setMainController(this);
    }

    public MenuController getMenuController() {
        return this.menuController;
    }

    public TableFilesController getTableFilesController() {
        return this.tableFilesController;
    }

    public void runAlgorithm(Project chosenProject, ArrayList<Project> chosenBase) {
        algorithm = new Algorithm(chosenProject, chosenBase);
        algorithm.runAlgorithm();
    }

}
