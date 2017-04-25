package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class SettingsTokensController implements Initializable {

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML @SuppressWarnings("unused")
    private void okAction(ActionEvent event) {
        stage.close();
    }

    @FXML @SuppressWarnings("unused")
    private void cancelAction(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
