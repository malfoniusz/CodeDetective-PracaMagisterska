package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Project;

public class MenuController implements Initializable {

    private MainController mainController;
    private Stage mainStage;

    private Project chosenProject;
    private ArrayList<Project> chosenBase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chosenProject = null;
        chosenBase = null;
    }

    @FXML
    private void startAction(ActionEvent event) {
        mainController.runAlgorithm(chosenProject, chosenBase);
    }

    @FXML
    private void chooseProjectAction(ActionEvent event) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Open Project Folder");
        File directory = dirChooser.showDialog(mainStage);

        if (directory != null) {
            chosenProject = new Project(directory);
        }
    }

    @FXML
    private void chooseBaseAction(ActionEvent event) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Open Base Folder");
        File directory = dirChooser.showDialog(mainStage);

        if (directory != null) {
            chosenBase = new ArrayList<Project>();

            File[] files = directory.listFiles();

            for (File file : files) {
                if (file.isDirectory()) {
                    Project project = new Project(file);
                    chosenBase.add(project);
                }
            }
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}