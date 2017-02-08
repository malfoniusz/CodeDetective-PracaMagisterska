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
import main.Algorithm;

public class MenuController implements Initializable {

    @FXML MenuItem iItemStart;

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
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Open Project Folder");
        File directory = dirChooser.showDialog(mainStage);

        if (directory != null) {
            algorithm.setProject(directory);
            updateIItemStart();
        }
    }

    @FXML
    private void chooseBaseAction(ActionEvent event) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Open Base Folder");
        File directory = dirChooser.showDialog(mainStage);

        if (directory != null) {
            algorithm.setBase(directory);
            updateIItemStart();
        }
    }

    public void updateIItemStart() {
        if (algorithm.getProject() != null && algorithm.getBase() != null) {
            iItemStart.setDisable(false);
        } else {
            iItemStart.setDisable(true);
        }
    }

    public void setDataFromMainController(MainController mainController) {
        this.mainController = mainController;
        this.algorithm = mainController.getAlgorithm();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}
