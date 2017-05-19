package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import staticc.Settings;

public class SettingsAlgorithmController implements Initializable {

    @FXML private Spinner<Integer> iSpinnerLines;
    @FXML private Spinner<Double> iSpinnerSimilarity;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int iSpinnerLinesValue = Settings.getMinimalMatchedLinesValue();
        iSpinnerLines.getValueFactory().setValue(iSpinnerLinesValue);

        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100);
        iSpinnerSimilarity.setValueFactory(valueFactory);
        valueFactory.setValue(Settings.getMinimalSimilarityAsPercentage());
    }

    @FXML @SuppressWarnings("unused")
    private void okAction(ActionEvent event) {
        commitSpinner(iSpinnerLines);
        int iSpinnerLinesValue = iSpinnerLines.getValue();
        Settings.setMinimalMatchedLinesValue(iSpinnerLinesValue);

        commitSpinner(iSpinnerSimilarity);
        double iSpinnerSimilarityValue = iSpinnerSimilarity.getValue();
        Settings.setMinimalSimilarityAsPercentage(iSpinnerSimilarityValue);

        stage.close();
    }

    @FXML @SuppressWarnings("unused")
    private void cancelAction(ActionEvent event) {
        stage.close();
    }

    private <T> void commitSpinner(Spinner<T> spinner) {
        if (!spinner.isEditable()) return;
        String text = spinner.getEditor().getText();
        SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                T value = converter.fromString(text);
                valueFactory.setValue(value);
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
