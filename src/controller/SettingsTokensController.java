package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import staticc.SettingsTokens;

public class SettingsTokensController implements Initializable {

    @FXML private CheckBox iNumber, iNumberWhole, iNumberDecimal, iInt, iLong, iShort, iFloat, iDouble;
    @FXML private CheckBox iClassVariable, iBoolean, iByte;
    @FXML private CheckBox iText, iString, iChar;
    @FXML private CheckBox iOperator, iRelation, iAssign, iLogic, iArithmetic, iBitwise;
    @FXML private CheckBox iIfAndElse, iSwitch, iCase, iDefault;
    @FXML private CheckBox iLoop, iFor, iWhile, iDo;
    @FXML private CheckBox iFunctionDefine, iFunctionUse;
    @FXML private CheckBox iStatic, iFinal, iThrows, iVoid;
    @FXML private CheckBox iTable, iCast, iGeneric;
    @FXML private CheckBox iClass, iNew, iEnum, iExtends, iImplements;
    @FXML private CheckBox iTry, iCatch, iThrow;
    @FXML private CheckBox iBreak, iContinue, iReturn;
    @FXML private CheckBox iSkipFunctionArgs, iSkipIfArgs, iSkipLoopArgs;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateNumber(SettingsTokens.getINumber(), SettingsTokens.getINumberWhole(), SettingsTokens.getIInt(), SettingsTokens.getILong(), SettingsTokens.getIShort(), SettingsTokens.getINumberDecimal(), SettingsTokens.getIFloat(), SettingsTokens.getIDouble());
        iClassVariable.setSelected(SettingsTokens.getIClassVariable());
        iBoolean.setSelected(SettingsTokens.getIBoolean());
        iByte.setSelected(SettingsTokens.getIByte());
        updateText(SettingsTokens.getIText(), SettingsTokens.getIString(), SettingsTokens.getIChar());
        updateOperator(SettingsTokens.getIOperator(), SettingsTokens.getIRelation(), SettingsTokens.getIAssign(), SettingsTokens.getILogic(), SettingsTokens.getIArithmetic(), SettingsTokens.getIBitwise());
        iIfAndElse.setSelected(SettingsTokens.getIIfAndElse());
        iSwitch.setSelected(SettingsTokens.getISwitch());
        iCase.setSelected(SettingsTokens.getICase());
        iDefault.setSelected(SettingsTokens.getIDefault());
        updateLoopGroup(SettingsTokens.getILoop(), SettingsTokens.getIFor(), SettingsTokens.getIWhile(), SettingsTokens.getIDo());
        iFunctionDefine.setSelected(SettingsTokens.getIFunctionDefine());
        iFunctionUse.setSelected(SettingsTokens.getIFunctionUse());
        iStatic.setSelected(SettingsTokens.getIStatic());
        iFinal.setSelected(SettingsTokens.getIFinal());
        iThrows.setSelected(SettingsTokens.getIThrows());
        iVoid.setSelected(SettingsTokens.getIVoid());
        iTable.setSelected(SettingsTokens.getITable());
        iCast.setSelected(SettingsTokens.getICast());
        iGeneric.setSelected(SettingsTokens.getIGeneric());
        iClass.setSelected(SettingsTokens.getIClass());
        iNew.setSelected(SettingsTokens.getINew());
        iEnum.setSelected(SettingsTokens.getIEnum());
        iExtends.setSelected(SettingsTokens.getIExtends());
        iImplements.setSelected(SettingsTokens.getIImplements());
        iTry.setSelected(SettingsTokens.getITry());
        iCatch.setSelected(SettingsTokens.getICatch());
        iThrow.setSelected(SettingsTokens.getIThrow());
        iBreak.setSelected(SettingsTokens.getIBreak());
        iContinue.setSelected(SettingsTokens.getIContinue());
        iReturn.setSelected(SettingsTokens.getIReturn());
        iSkipFunctionArgs.setSelected(SettingsTokens.getISkipFunctionArgs());
        iSkipIfArgs.setSelected(SettingsTokens.getISkipIfArgs());
        iSkipLoopArgs.setSelected(SettingsTokens.getISkipLoopArgs());

        updateSkipFunctionArgs();
        updateSkipIfArgs();
        updateSkipLoopArgs();
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
    private void actionLoopAndSkip(ActionEvent event) {
        updateLoopGroup(iLoop.isSelected(), true, true, true);

        updateSkipLoopArgs();
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

        SettingsTokens.setINumber(iNumber.isSelected());
        SettingsTokens.setINumberWhole(iNumberWhole.isSelected());
        SettingsTokens.setINumberDecimal(iNumberDecimal.isSelected());
        SettingsTokens.setIInt(iInt.isSelected());
        SettingsTokens.setILong(iLong.isSelected());
        SettingsTokens.setIShort(iShort.isSelected());
        SettingsTokens.setIFloat(iFloat.isSelected());
        SettingsTokens.setIDouble(iDouble.isSelected());
        SettingsTokens.setIClassVariable(iClassVariable.isSelected());
        SettingsTokens.setIBoolean(iBoolean.isSelected());
        SettingsTokens.setIByte(iByte.isSelected());
        SettingsTokens.setIText(iText.isSelected());
        SettingsTokens.setIString(iString.isSelected());
        SettingsTokens.setIChar(iChar.isSelected());
        SettingsTokens.setIOperator(iOperator.isSelected());
        SettingsTokens.setIRelation(iRelation.isSelected());
        SettingsTokens.setIAssign(iAssign.isSelected());
        SettingsTokens.setILogic(iLogic.isSelected());
        SettingsTokens.setIArithmetic(iArithmetic.isSelected());
        SettingsTokens.setIBitwise(iBitwise.isSelected());
        SettingsTokens.setIIfAndElse(iIfAndElse.isSelected());
        SettingsTokens.setISwitch(iSwitch.isSelected());
        SettingsTokens.setICase(iCase.isSelected());
        SettingsTokens.setIDefault(iDefault.isSelected());
        SettingsTokens.setILoop(iLoop.isSelected());
        SettingsTokens.setIFor(iFor.isSelected());
        SettingsTokens.setIWhile(iWhile.isSelected());
        SettingsTokens.setIDo(iDo.isSelected());
        SettingsTokens.setIFunctionDefine(iFunctionDefine.isSelected());
        SettingsTokens.setIFunctionUse(iFunctionUse.isSelected());
        SettingsTokens.setIStatic(iStatic.isSelected());
        SettingsTokens.setIFinal(iFinal.isSelected());
        SettingsTokens.setIThrows(iThrows.isSelected());
        SettingsTokens.setIVoid(iVoid.isSelected());
        SettingsTokens.setITable(iTable.isSelected());
        SettingsTokens.setICast(iCast.isSelected());
        SettingsTokens.setIGeneric(iGeneric.isSelected());
        SettingsTokens.setIClass(iClass.isSelected());
        SettingsTokens.setINew(iNew.isSelected());
        SettingsTokens.setIEnum(iEnum.isSelected());
        SettingsTokens.setIExtends(iExtends.isSelected());
        SettingsTokens.setIImplements(iImplements.isSelected());
        SettingsTokens.setITry(iTry.isSelected());
        SettingsTokens.setICatch(iCatch.isSelected());
        SettingsTokens.setIThrow(iThrow.isSelected());
        SettingsTokens.setIBreak(iBreak.isSelected());
        SettingsTokens.setIContinue(iContinue.isSelected());
        SettingsTokens.setIReturn(iReturn.isSelected());
        SettingsTokens.setISkipFunctionArgs(iSkipFunctionArgs.isSelected());
        SettingsTokens.setISkipIfArgs(iSkipIfArgs.isSelected());
        SettingsTokens.setISkipLoopArgs(iSkipLoopArgs.isSelected());
    }

    @FXML @SuppressWarnings("unused")
    private void actionControlSkipFuntionArgs(ActionEvent event) {
        updateSkipFunctionArgs();
    }

    private void updateSkipFunctionArgs() {
        if (iFunctionDefine.isSelected() == false && iFunctionUse.isSelected() == false) {
            iSkipFunctionArgs.setDisable(true);
            iSkipFunctionArgs.setSelected(true);
        }
        else {
            iSkipFunctionArgs.setDisable(false);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionControlSkipStatmentArgs(ActionEvent event) {
        updateSkipIfArgs();
    }

    private void updateSkipIfArgs() {
        if (iIfAndElse.isSelected() == false) {
            iSkipIfArgs.setDisable(true);
            iSkipIfArgs.setSelected(true);
        }
        else {
            iSkipIfArgs.setDisable(false);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionControlSkipLoopArgs(ActionEvent event) {
        updateSkipLoopArgs();
    }

    private void updateSkipLoopArgs() {
        if (iLoop.isSelected() == false && iFor.isSelected() == false && iWhile.isSelected() == false) {
            iSkipLoopArgs.setDisable(true);
            iSkipLoopArgs.setSelected(true);
        }
        else {
            iSkipLoopArgs.setDisable(false);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionCancel(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
