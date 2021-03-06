package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Project;
import model.Projects;
import staticc.PropertiesReader;
import staticc.Settings;

public class MenuController implements Initializable {

    @FXML private MenuItem iItemStart;

    private MainController mainController;
    private Stage mainStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML @SuppressWarnings("unused")
    private void startAction(ActionEvent event) {
        mainController.getCompareFragmentsController().clearData();
        mainController.getCompareCodesController().clearCodes();

        mainController.runAlgorithm();
    }

    @FXML @SuppressWarnings("unused")
    private void chooseProjectAction(ActionEvent event) {
        File directory = chooseDirectory("Open Project Folder");

        if (directory != null) {
            Project project = new Project(directory);
            Settings.setProject(project);
            updateIItemStart();
        }
    }

    @FXML @SuppressWarnings("unused")
    private void chooseBaseAction(ActionEvent event) {
        File directory = chooseDirectory("Open Base Folder");

        if (directory != null) {
            Projects projects = new Projects(directory);
            Settings.setBase(projects);
            updateIItemStart();
        }
    }

    private File chooseDirectory(String title) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle(title);
        return dirChooser.showDialog(mainStage);
    }

    @FXML @SuppressWarnings("unused")
    private void algorithmSettingAction(ActionEvent event) throws IOException {
        String algorithmSettingsFilePath = PropertiesReader.readProperty("menuController_algorithmSettingAction");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(algorithmSettingsFilePath));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        SettingsAlgorithmController controller = (SettingsAlgorithmController) loader.getController();
        controller.setStage(stage);

        stage.setTitle("Algorithm Settings");
        // Usuniecie przyciskow minimalizacji i maksymalizacji
        stage.initStyle(StageStyle.UTILITY);
        // Zablokowanie zamkniecia poprzedniego okna dopoki te okno nie zostanie zamkniete
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML @SuppressWarnings("unused")
    private void settingsTokensAction(ActionEvent event) throws IOException {
        String settingsTokensFilePath = PropertiesReader.readProperty("menuController_settingsTokensAction");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(settingsTokensFilePath));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        SettingsTokensController controller = (SettingsTokensController) loader.getController();
        controller.setStage(stage);

        stage.setTitle("Tokens Settings");
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML @SuppressWarnings("unused")
    private void helpAction(ActionEvent event) throws IOException {
        String settingsTokensFilePath = PropertiesReader.readProperty("menuController_helpAction");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(settingsTokensFilePath));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Help");
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void updateIItemStart() {
        if (Settings.getProject() != null && Settings.getBase() != null) {
            iItemStart.setDisable(false);
        } else {
            iItemStart.setDisable(true);
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}
