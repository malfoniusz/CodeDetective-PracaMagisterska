package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import model.ConsecutiveOption;
import staticc.Settings;

public class SettingsAlgorithmController implements Initializable {

    @FXML private Spinner<Integer> iSpinnerLines;
    @FXML private Spinner<Integer> iSpinnerTokens;
    @FXML private RadioButton iRadioLines;
    @FXML private RadioButton iRadioTokens;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int iSpinnerLinesValue = Settings.getConsecutiveLinesValue();
        iSpinnerLines.getValueFactory().setValue(iSpinnerLinesValue);

        int iSpinnerTokensValue = Settings.getConsecutiveTokensValue();
        iSpinnerTokens.getValueFactory().setValue(iSpinnerTokensValue);

        String consecutiveOption = Settings.getConsecutiveOption();
        if (consecutiveOption.equals(ConsecutiveOption.LINES.toString())) {
            iRadioLines.setSelected(true);
            radioLinesSelected();
        }
        else {
            iRadioTokens.setSelected(true);
            radioTokensSelected();
        }
    }

    @FXML
    private void radioLinesAction(ActionEvent event) {
        radioLinesSelected();
    }

    private void radioLinesSelected() {
        iSpinnerLines.setDisable(false);
        iSpinnerTokens.setDisable(true);
    }

    @FXML
    private void radioTokensAction(ActionEvent event) {
        radioTokensSelected();
    }

    private void radioTokensSelected() {
        iSpinnerLines.setDisable(true);
        iSpinnerTokens.setDisable(false);
    }

    @FXML
    private void okAction(ActionEvent event) {
        int iSpinnerLinesValue = iSpinnerLines.getValue();
        Settings.setConsecutiveLinesValue(iSpinnerLinesValue);

        int iSpinnerTokensValue = iSpinnerTokens.getValue();
        Settings.setConsecutiveTokensValue(iSpinnerTokensValue);

        String consecutiveOption = iRadioLines.isSelected() ? ConsecutiveOption.LINES.toString() : ConsecutiveOption.TOKENS.toString();
        Settings.setConsecutiveOption(consecutiveOption);

        stage.close();
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}