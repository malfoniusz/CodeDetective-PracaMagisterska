package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import staticc.Settings;

public class SettingsAlgorithmController implements Initializable {

    @FXML private Spinner<Integer> iSpinnerLines;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int iSpinnerLinesValue = Settings.getMinimalMatchedLinesValue();
        iSpinnerLines.getValueFactory().setValue(iSpinnerLinesValue);
    }

    @FXML @SuppressWarnings("unused")
    private void okAction(ActionEvent event) {
        int iSpinnerLinesValue = iSpinnerLines.getValue();
        Settings.setMinimalMatchedLinesValue(iSpinnerLinesValue);

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
