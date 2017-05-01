package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import staticc.Settings;

public class SettingsTokensController implements Initializable {

    @FXML private CheckBox iNumber, iNumberWhole, iNumberDecimal, iInt, iLong, iShort, iFloat, iDouble;
    @FXML private CheckBox iClassVariable, iBoolean, iByte;
    @FXML private CheckBox iText, iString, iChar;
    @FXML private CheckBox iOperator, iRelation, iAssign, iLogic, iArithmetic, iBitwise;
    @FXML private CheckBox iIfAndElse, iSwitch, iCase, iDefault;
    @FXML private CheckBox iLoop, iFor, iWhile, iDo;
    @FXML private CheckBox iFunctionDefine, iFunctionUse, iConstructorUse;
    @FXML private CheckBox iStatic, iFinal, iThrows, iVoid;
    @FXML private CheckBox iTable, iCast, iGeneric;
    @FXML private CheckBox iClass, iNew, iEnum, iExtends, iImplements;
    @FXML private CheckBox iTry, iCatch, iThrow;
    @FXML private CheckBox iBreak, iContinue, iReturn;
    @FXML private CheckBox iSkipFunctionArgs, iSkipStatmentArgs, iSkipLoopArgs;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateNumber(Settings.getINunmber(), Settings.getINumberWhole(), Settings.getIInt(), Settings.getILong(), Settings.getIShort(), Settings.getINumberDecimal(), Settings.getIFloat(), Settings.getIDouble());
        iClassVariable.setSelected(Settings.getIClassVariable());
        iBoolean.setSelected(Settings.getIBoolean());
        iByte.setSelected(Settings.getIByte());
        updateText(Settings.getIText(), Settings.getIString(), Settings.getIChar());
        updateOperator(Settings.getIOperator(), Settings.getIRelation(), Settings.getIAssign(), Settings.getILogic(), Settings.getIArithmetic(), Settings.getIBitwise());
        iIfAndElse.setSelected(Settings.getIIfAndElse());
        iSwitch.setSelected(Settings.getISwitch());
        iCase.setSelected(Settings.getICase());
        iDefault.setSelected(Settings.getIDefault());
        updateLoopGroup(Settings.getILoop(), Settings.getIFor(), Settings.getIWhile(), Settings.getIDo());
        iFunctionDefine.setSelected(Settings.getIFunctionDefine());
        iFunctionUse.setSelected(Settings.getIFunctionUse());
        iConstructorUse.setSelected(Settings.getIConstructorUse());
        iStatic.setSelected(Settings.getIStatic());
        iFinal.setSelected(Settings.getIFinal());
        iThrows.setSelected(Settings.getIThrows());
        iVoid.setSelected(Settings.getIVoid());
        iTable.setSelected(Settings.getITable());
        iCast.setSelected(Settings.getICast());
        iGeneric.setSelected(Settings.getIGeneric());
        iClass.setSelected(Settings.getIClass());
        iNew.setSelected(Settings.getINew());
        iEnum.setSelected(Settings.getIEnum());
        iExtends.setSelected(Settings.getIExtends());
        iImplements.setSelected(Settings.getIImplements());
        iTry.setSelected(Settings.getITry());
        iCatch.setSelected(Settings.getICatch());
        iThrow.setSelected(Settings.getIThrow());
        iBreak.setSelected(Settings.getIBreak());
        iContinue.setSelected(Settings.getIContinue());
        iReturn.setSelected(Settings.getIReturn());
        iSkipFunctionArgs.setSelected(Settings.getISkipFunctionArgs());
        iSkipStatmentArgs.setSelected(Settings.getISkipStatmentArgs());
        iSkipLoopArgs.setSelected(Settings.getISkipLoopArgs());
    }

    @FXML @SuppressWarnings("unused")
    private void actionNumber(ActionEvent event) {
        updateNumber(iNumber.isSelected(), true, true, true, true, true, true, true);
    }

    private void updateNumber(boolean _iNumber,
            boolean _iNumberWhole, boolean _iInt, boolean _iLong, boolean _iShort,
            boolean _iNumberDecimal, boolean _iFloat, boolean _Double) {
        iNumber.setSelected(_iNumber);

        if (iNumber.isSelected()) {
            iNumberWhole.setDisable(true);
            iNumberDecimal.setDisable(true);

            updateNumberWhole(true, false, false, false);
            updateNumberDecimal(true, false, false);

            iNumberWhole.setSelected(false);
            iNumberDecimal.setSelected(false);
        }
        else {
            iNumberWhole.setDisable(false);
            iNumberDecimal.setDisable(false);

            updateNumberWhole(_iNumberWhole, _iInt, _iLong, _iShort);
            updateNumberDecimal(_iNumberDecimal, _iFloat, _Double);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionNumberWhole(ActionEvent event) {
        updateNumberWhole(iNumberWhole.isSelected(), true, true, true);
    }

    private void updateNumberWhole(boolean _iNumberWhole, boolean _iInt, boolean _iLong, boolean _iShort) {
        iNumberWhole.setSelected(_iNumberWhole);

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

            iInt.setSelected(_iInt);
            iLong.setSelected(_iLong);
            iShort.setSelected(_iShort);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionNumberDecimal(ActionEvent event) {
        updateNumberDecimal(iNumberDecimal.isSelected(), true, true);
    }

    private void updateNumberDecimal(boolean _iNumberDecimal, boolean _iFloat, boolean _iDouble) {
        iNumberDecimal.setSelected(_iNumberDecimal);

        if (iNumberDecimal.isSelected()) {
            iFloat.setDisable(true);
            iDouble.setDisable(true);

            iFloat.setSelected(false);
            iDouble.setSelected(false);
        }
        else {
            iFloat.setDisable(false);
            iDouble.setDisable(false);

            iFloat.setSelected(_iFloat);
            iDouble.setSelected(_iDouble);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionText(ActionEvent event) {
        updateText(iText.isSelected(), true, true);
    }

    private void updateText(boolean _iText, boolean _iString, boolean _iChar) {
        iText.setSelected(_iText);

        if (iText.isSelected()) {
            iString.setDisable(true);
            iChar.setDisable(true);

            iString.setSelected(false);
            iChar.setSelected(false);
        }
        else {
            iString.setDisable(false);
            iChar.setDisable(false);

            iString.setSelected(_iString);
            iChar.setSelected(_iChar);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionOperator(ActionEvent event) {
        updateOperator(iOperator.isSelected(), true, true, true, true, true);
    }

    private void updateOperator(boolean _iOperation, boolean _iRelation, boolean _iAssign, boolean _iLogic, boolean _iArithmetic, boolean _iBitwise) {
        iOperator.setSelected(_iOperation);

        if (iOperator.isSelected()) {
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

            iRelation.setSelected(_iRelation);
            iAssign.setSelected(_iAssign);
            iLogic.setSelected(_iLogic);
            iArithmetic.setSelected(_iArithmetic);
            iBitwise.setSelected(_iBitwise);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionLoop(ActionEvent event) {
        updateLoopGroup(iLoop.isSelected(), true, true, true);
    }

    private void updateLoopGroup(boolean _iLoop, boolean _iFor, boolean _iWhile, boolean _iDo) {
        iLoop.setSelected(_iLoop);

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

            iFor.setSelected(_iFor);
            iWhile.setSelected(_iWhile);
            iDo.setSelected(_iDo);
        }
    }


    @FXML @SuppressWarnings("unused")
    private void actionOk(ActionEvent event) {
        stage.close();

        Settings.setINumber(iNumber.isSelected());
        Settings.setINumberWhole(iNumberWhole.isSelected());
        Settings.setINumberDecimal(iNumberDecimal.isSelected());
        Settings.setIInt(iInt.isSelected());
        Settings.setILong(iLong.isSelected());
        Settings.setIShort(iShort.isSelected());
        Settings.setIFloat(iFloat.isSelected());
        Settings.setIDouble(iDouble.isSelected());
        Settings.setIClassVariable(iClassVariable.isSelected());
        Settings.setIBoolean(iBoolean.isSelected());
        Settings.setIByte(iByte.isSelected());
        Settings.setIText(iText.isSelected());
        Settings.setIString(iString.isSelected());
        Settings.setIChar(iChar.isSelected());
        Settings.setIOperator(iOperator.isSelected());
        Settings.setIRelation(iRelation.isSelected());
        Settings.setIAssign(iAssign.isSelected());
        Settings.setILogic(iLogic.isSelected());
        Settings.setIArithmetic(iArithmetic.isSelected());
        Settings.setIBitwise(iBitwise.isSelected());
        Settings.setIIfAndElse(iIfAndElse.isSelected());
        Settings.setISwitch(iSwitch.isSelected());
        Settings.setICase(iCase.isSelected());
        Settings.setIDefault(iDefault.isSelected());
        Settings.setILoop(iLoop.isSelected());
        Settings.setIFor(iFor.isSelected());
        Settings.setIWhile(iWhile.isSelected());
        Settings.setIDo(iDo.isSelected());
        Settings.setIFunctionDefine(iFunctionDefine.isSelected());
        Settings.setIFunctionUse(iFunctionUse.isSelected());
        Settings.setIConstructorUse(iConstructorUse.isSelected());
        Settings.setIStatic(iStatic.isSelected());
        Settings.setIFinal(iFinal.isSelected());
        Settings.setIThrows(iThrows.isSelected());
        Settings.setIVoid(iVoid.isSelected());
        Settings.setITable(iTable.isSelected());
        Settings.setICast(iCast.isSelected());
        Settings.setIGeneric(iGeneric.isSelected());
        Settings.setIClass(iClass.isSelected());
        Settings.setINew(iNew.isSelected());
        Settings.setIEnum(iEnum.isSelected());
        Settings.setIExtends(iExtends.isSelected());
        Settings.setIImplements(iImplements.isSelected());
        Settings.setITry(iTry.isSelected());
        Settings.setICatch(iCatch.isSelected());
        Settings.setIThrow(iThrow.isSelected());
        Settings.setIBreak(iBreak.isSelected());
        Settings.setIContinue(iContinue.isSelected());
        Settings.setIReturn(iReturn.isSelected());
        Settings.setISkipFunctionArgs(iSkipFunctionArgs.isSelected());
        Settings.setISkipStatmentArgs(iSkipStatmentArgs.isSelected());
        Settings.setISkipLoopArgs(iSkipLoopArgs.isSelected());
    }

    @FXML @SuppressWarnings("unused")
    private void actionCancel(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
