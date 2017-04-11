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
    private Compare compare;
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
            compare.getSettings().setProject(project);
            updateIItemStart();
        }
    }

    @FXML
    private void chooseBaseAction(ActionEvent event) {
        File directory = chooseDirectory("Open Base Folder");

        if (directory != null) {
            Projects projects = new Projects(directory);
            compare.getSettings().setBase(projects);
            updateIItemStart();
        }
    }

    private File chooseDirectory(String title) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle(title);
        return dirChooser.showDialog(mainStage);
    }

    public void updateIItemStart() {
        if (compare.getSettings().getProject() != null && compare.getSettings().getBase() != null) {
            iItemStart.setDisable(false);
        } else {
            iItemStart.setDisable(true);
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setCompare(Compare compare) {
        this.compare = compare;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}
