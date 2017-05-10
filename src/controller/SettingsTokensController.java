package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.SetttingsTokensRadioGroup;
import staticc.SettingsTokens;

public class SettingsTokensController implements Initializable {

    @FXML private RadioButton iRadioFull, iRadioNormal, iRadioMinimalistic, iRadioCustom;

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
        setRadioSelected(SettingsTokens.getIRadioGroup());

        updateNumber(SettingsTokens.getINumber(), SettingsTokens.getINumberWhole(), SettingsTokens.getINumberDecimal(), SettingsTokens.getIInt(), SettingsTokens.getILong(), SettingsTokens.getIShort(), SettingsTokens.getIFloat(), SettingsTokens.getIDouble());
        setOptions1(SettingsTokens.getIClassVariable(), SettingsTokens.getIBoolean(), SettingsTokens.getIByte());
        updateText(SettingsTokens.getIText(), SettingsTokens.getIString(), SettingsTokens.getIChar());
        updateOperator(SettingsTokens.getIOperator(), SettingsTokens.getIRelation(), SettingsTokens.getIAssign(), SettingsTokens.getILogic(), SettingsTokens.getIArithmetic(), SettingsTokens.getIBitwise());
        setOptions2(SettingsTokens.getIIfAndElse(), SettingsTokens.getISwitch(), SettingsTokens.getICase(), SettingsTokens.getIDefault());
        updateLoopGroup(SettingsTokens.getILoop(), SettingsTokens.getIFor(), SettingsTokens.getIWhile(), SettingsTokens.getIDo());
        setOptions3(SettingsTokens.getIFunctionDefine(), SettingsTokens.getIFunctionUse());
        setOptions4(SettingsTokens.getIStatic(), SettingsTokens.getIFinal(), SettingsTokens.getIThrows(), SettingsTokens.getIVoid());
        setOptions5(SettingsTokens.getITable(), SettingsTokens.getICast(), SettingsTokens.getIGeneric());
        setOptions6(SettingsTokens.getIClass(), SettingsTokens.getINew(), SettingsTokens.getIEnum(), SettingsTokens.getIExtends(), SettingsTokens.getIImplements());
        setOptions7(SettingsTokens.getITry(), SettingsTokens.getICatch(), SettingsTokens.getIThrow());
        setOptions8(SettingsTokens.getIBreak(), SettingsTokens.getIContinue(), SettingsTokens.getIReturn());
        setOptions9(SettingsTokens.getISkipFunctionArgs(), SettingsTokens.getISkipIfArgs(), SettingsTokens.getISkipLoopArgs());

        updateSkips();
    }

    private void setRadioSelected(String strRadio) {
        if (SetttingsTokensRadioGroup.FULL.toString().equals(strRadio)) {
            iRadioFull.setSelected(true);
        }
        else if (SetttingsTokensRadioGroup.NORMAL.toString().equals(strRadio)) {
            iRadioNormal.setSelected(true);
        }
        else if (SetttingsTokensRadioGroup.MINIMALISTIC.toString().equals(strRadio)) {
            iRadioMinimalistic.setSelected(true);
        }
        else if (SetttingsTokensRadioGroup.CUSTOM.toString().equals(strRadio)) {
            iRadioCustom.setSelected(true);
        }
    }

    @FXML @SuppressWarnings("unused")
    private void actionRadioFull(ActionEvent event) {
        updateNumber(false, false, false, true, true, true, true, true);
        setOptions1(true, true, true);
        updateText(false, true, true);
        updateOperator(false, true, true, true, true, true);
        setOptions2(true, true, true, true);
        updateLoopGroup(false, true, true, true);
        setOptions3(true, true);
        setOptions4(true, true, true, true);
        setOptions5(true, true, true);
        setOptions6(true, true, true, true, true);
        setOptions7(true, true, true);
        setOptions8(true, true, true);
        setOptions9(false, false, false);
        updateSkips();
    }

    @FXML @SuppressWarnings("unused")
    private void actionRadioNormal(ActionEvent event) {
    	updateNumber(false, true, true, false, false, false, false, false);
        setOptions1(true, true, true);
        updateText(true, false, false);
        updateOperator(true, false, false, false, false, false);
        setOptions2(true, true, true, true);
        updateLoopGroup(true, false, false, false);
        setOptions3(true, true);
        setOptions4(true, true, true, true);
        setOptions5(true, true, true);
        setOptions6(true, true, true, true, true);
        setOptions7(true, true, true);
        setOptions8(true, true, true);
        setOptions9(false, true, true);
        updateSkips();
    }

    @FXML @SuppressWarnings("unused")
    private void actionRadioMinimalistic(ActionEvent event) {
    	updateNumber(true, false, false, false, false, false, false, false);
        setOptions1(true, true, true);
        updateText(true, false, false);
        updateOperator(true, false, false, false, false, false);
        setOptions2(true, true, false, false);
        updateLoopGroup(true, false, false, false);
        setOptions3(true, true);
        setOptions4(false, false, false, true);
        setOptions5(false, false, false);
        setOptions6(true, false, false, false, false);
        setOptions7(true, true, false);
        setOptions8(false, false, false);
        setOptions9(true, true, true);
        updateSkips();
    }

    private void setOptions1(boolean iClassVariable, boolean iBoolean, boolean iByte) {
        this.iClassVariable.setSelected(iClassVariable);
        this.iBoolean.setSelected(iBoolean);
        this.iByte.setSelected(iByte);
    }

    private void setOptions2(boolean iIfAndElse, boolean iSwitch, boolean iCase, boolean iDefault) {
        this.iIfAndElse.setSelected(iIfAndElse);
        this.iSwitch.setSelected(iSwitch);
        this.iCase.setSelected(iCase);
        this.iDefault.setSelected(iDefault);
    }

    private void setOptions3(boolean iFunctionDefine, boolean iFunctionUse) {
        this.iFunctionDefine.setSelected(iFunctionDefine);
        this.iFunctionUse.setSelected(iFunctionUse);
    }

    private void setOptions4(boolean iStatic, boolean iFinal, boolean iThrows, boolean iVoid) {
        this.iStatic.setSelected(iStatic);
        this.iFinal.setSelected(iFinal);
        this.iThrows.setSelected(iThrows);
        this.iVoid.setSelected(iVoid);
    }

    private void setOptions5(boolean iTable, boolean iCast, boolean iGeneric) {
        this.iTable.setSelected(iTable);
        this.iCast.setSelected(iCast);
        this.iGeneric.setSelected(iGeneric);
    }

    private void setOptions6(boolean iClass, boolean iNew, boolean iEnum, boolean iExtends, boolean iImplements) {
        this.iClass.setSelected(iClass);
        this.iNew.setSelected(iNew);
        this.iEnum.setSelected(iEnum);
        this.iExtends.setSelected(iExtends);
        this.iImplements.setSelected(iImplements);
    }

    private void setOptions7(boolean iTry, boolean iCatch, boolean iThrow) {
        this.iTry.setSelected(iTry);
        this.iCatch.setSelected(iCatch);
        this.iThrow.setSelected(iThrow);
    }

    private void setOptions8(boolean iBreak, boolean iContinue, boolean iReturn) {
        this.iBreak.setSelected(iBreak);
        this.iContinue.setSelected(iContinue);
        this.iReturn.setSelected(iReturn);
    }

    private void setOptions9(boolean iSkipFunctionArgs, boolean iSkipIfArgs, boolean iSkipLoopArgs) {
        this.iSkipFunctionArgs.setSelected(iSkipFunctionArgs);
        this.iSkipIfArgs.setSelected(iSkipIfArgs);
        this.iSkipLoopArgs.setSelected(iSkipLoopArgs);
    }

    @FXML @SuppressWarnings("unused")
    private void actionNumber(ActionEvent event) {
        updateNumber(iNumber.isSelected(), true, true, true, true, true, true, true);

        selectRadioCustom();
    }

    private void updateNumber(boolean _iNumber,
            boolean _iNumberWhole, boolean _iNumberDecimal,
            boolean _iInt, boolean _iLong, boolean _iShort,
            boolean _iFloat, boolean _Double) {
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

        selectRadioCustom();
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

        selectRadioCustom();
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

        selectRadioCustom();
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

        selectRadioCustom();
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

        updateSkipLoopArgs();

        selectRadioCustom();
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
    private void actionControlSkipFuntionArgs(ActionEvent event) {
        updateSkipFunctionArgs();

        selectRadioCustom();
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

        selectRadioCustom();
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

        selectRadioCustom();
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

    private void updateSkips() {
        updateSkipFunctionArgs();
        updateSkipIfArgs();
        updateSkipLoopArgs();
    }

    @FXML @SuppressWarnings("unused")
    private void actionOk(ActionEvent event) {
        stage.close();

        SettingsTokens.setIRadioGroup(getRadioGroup());

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
    private void actionCancel(ActionEvent event) {
        stage.close();
    }

    private SetttingsTokensRadioGroup getRadioGroup() {
        if (iRadioFull.isSelected()) {
            return SetttingsTokensRadioGroup.FULL;
        }
        else if (iRadioNormal.isSelected()) {
            return SetttingsTokensRadioGroup.NORMAL;
        }
        else if (iRadioMinimalistic.isSelected()) {
            return SetttingsTokensRadioGroup.MINIMALISTIC;
        }
        else if (iRadioCustom.isSelected()) {
            return SetttingsTokensRadioGroup.CUSTOM;
        }

        return null;
    }

    @FXML @SuppressWarnings("unused")
    private void actionSelectRadioCustom(ActionEvent event) {
        selectRadioCustom();
    }

    private void selectRadioCustom() {
        iRadioCustom.setSelected(true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
