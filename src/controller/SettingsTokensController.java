package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class SettingsTokensController implements Initializable {

    @FXML private CheckBox iNumber, iNumberWhole, iNumberDecimal, iInt, iLong, iShort, iFloat, iDouble;
    @FXML private CheckBox iClassVariable, iBoolean, iByte;
    @FXML private CheckBox iOperation, iRelation, iAssign, iLogic, iArithmetic, iBitwise;
    @FXML private CheckBox iIfAndElse, iSwitch, iCase, iDefault;
    @FXML private CheckBox iLoop, iFor, iWhile, iDo;
    @FXML private CheckBox iFunctionDefine, iFunctionUse, iConstructorUse;
    @FXML private CheckBox iStatic, iFinal, iThrows, iVoid;
    @FXML private CheckBox iTable, iCast, iGeneric;
    @FXML private CheckBox iClass, iNew, iEnum, iExtends, iImplements;
    @FXML private CheckBox iTry, iCatch, iThrow;
    @FXML private CheckBox iBreak, iContinue, iReturn;
    @FXML private CheckBox iSkipFuntionArgs, iSkipStatmentArgs, iSkipLoopArgs;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void actionNumber(ActionEvent event) {
        if (iNumber.isSelected()) {
            iNumberWhole.setDisable(true);
            iNumberDecimal.setDisable(true);

            iNumberWhole.setSelected(true);
            iNumberDecimal.setSelected(true);
            actionNumberWhole(event);
            actionNumberDecimal(event);
            iNumberWhole.setSelected(false);
            iNumberDecimal.setSelected(false);
        }
        else {
            iNumberWhole.setDisable(false);
            iNumberDecimal.setDisable(false);

            iNumberWhole.setSelected(true);
            iNumberDecimal.setSelected(true);

            actionNumberWhole(event);
            actionNumberDecimal(event);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionNumberWhole(ActionEvent event) {
        if (iNumberWhole.isSelected()) {
            iInt.setDisable(true);
            iLong.setDisable(true);
            iShort.setDisable(true);

            iInt.setSelected(false);
            iLong.setSelected(false);
            iShort.setSelected(false);
        }
        else {
            iInt.setDisable(false);
            iLong.setDisable(false);
            iShort.setDisable(false);

            iInt.setSelected(true);
            iLong.setSelected(true);
            iShort.setSelected(true);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionNumberDecimal(ActionEvent event) {
        if (iNumberDecimal.isSelected()) {
            iFloat.setDisable(true);
            iDouble.setDisable(true);

            iFloat.setSelected(false);
            iDouble.setSelected(false);
        }
        else {
            iFloat.setDisable(false);
            iDouble.setDisable(false);

            iFloat.setSelected(true);
            iDouble.setSelected(true);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionOperation(ActionEvent event) {
        if (iOperation.isSelected()) {
            iRelation.setDisable(true);
            iAssign.setDisable(true);
            iLogic.setDisable(true);
            iArithmetic.setDisable(true);
            iBitwise.setDisable(true);

            iRelation.setSelected(false);
            iAssign.setSelected(false);
            iLogic.setSelected(false);
            iArithmetic.setSelected(false);
            iBitwise.setSelected(false);
        }
        else {
            iRelation.setDisable(false);
            iAssign.setDisable(false);
            iLogic.setDisable(false);
            iArithmetic.setDisable(false);
            iBitwise.setDisable(false);

            iRelation.setSelected(true);
            iAssign.setSelected(true);
            iLogic.setSelected(true);
            iArithmetic.setSelected(true);
            iBitwise.setSelected(true);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionLoop(ActionEvent event) {
        if (iLoop.isSelected()) {
            iFor.setDisable(true);
            iWhile.setDisable(true);
            iDo.setDisable(true);

            iFor.setSelected(false);
            iWhile.setSelected(false);
            iDo.setSelected(false);
        }
        else {
            iFor.setDisable(false);
            iWhile.setDisable(false);
            iDo.setDisable(false);

            iFor.setSelected(true);
            iWhile.setSelected(true);
            iDo.setSelected(true);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionOk(ActionEvent event) {
        stage.close();
    }

    @FXML @SuppressWarnings("unused")
    private void actionCancel(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
