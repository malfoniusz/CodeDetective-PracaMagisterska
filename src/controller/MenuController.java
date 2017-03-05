package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Project;
import model.Projects;

public class MenuController implements Initializable {

    @FXML private MenuItem iItemStart;

    private MainController mainController;
    private Algorithm algorithm;
    private Stage mainStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void startAction(ActionEvent event) {
        mainController.runAlgorithm();
    }

    @FXML
    private void chooseProjectAction(ActionEvent event) {
        File directory = chooseDirectory("Open Project Folder");

        if (directory != null) {
            Project project = new Project(directory);
            algorithm.getAlgorithmData().setProject(project);
            updateIItemStart();
        }
    }

    @FXML
    private void chooseBaseAction(ActionEvent event) {
        File directory = chooseDirectory("Open Base Folder");

        if (directory != null) {
            Projects projects = new Projects(directory);
            algorithm.getAlgorithmData().setProjects(projects);
            updateIItemStart();
        }
    }

    private File chooseDirectory(String title) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle(title);
        return dirChooser.showDialog(mainStage);
    }

    public void updateIItemStart() {
        if (algorithm.getAlgorithmData().getProject() != null && algorithm.getAlgorithmData().getProjects() != null) {
            iItemStart.setDisable(false);
        } else {
            iItemStart.setDisable(true);
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}
