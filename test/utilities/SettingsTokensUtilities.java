package utilities;

import staticc.SettingsTokens;

public class SettingsTokensUtilities {

	private SettingsTokensUtilities() {

	}

	public static void tokenizationTypeFull() {
    	setOptions1(false, false, false, true, true, true, true, true);
        setOptions2(true, true, true);
        setOptions3(false, true, true);
        setOptions4(false, true, true, true, true, true);
        setOptions5(true, true, true, true);
        setOptions6(false, true, true, true);
        setOptions7(true, true);
        setOptions8(true, true, true, true);
        setOptions9(true, true, true);
        setOptions10(true, true, true, true, true);
        setOptions11(true, true, true);
        setOptions12(true, true, true);
        setOptions13(false, false, false);
    }

    public static void tokenizationTypeNormal() {
    	setOptions1(false, true, true, false, false, false, false, false);
    	setOptions2(true, true, true);
    	setOptions3(true, false, false);
    	setOptions4(true, false, false, false, false, false);
    	setOptions5(true, true, true, true);
    	setOptions6(true, false, false, false);
    	setOptions7(true, true);
    	setOptions8(true, true, true, true);
    	setOptions9(true, true, true);
    	setOptions10(true, true, true, true, true);
    	setOptions11(true, true, true);
    	setOptions12(true, true, true);
    	setOptions13(false, true, true);
	}

	public static void tokenizationTypeMinimalistic() {
		setOptions1(true, false, false, false, false, false, false, false);
		setOptions2(true, true, true);
		setOptions3(true, false, false);
		setOptions4(true, false, false, false, false, false);
		setOptions5(true, true, false, false);
		setOptions6(true, false, false, false);
		setOptions7(true, true);
		setOptions8(false, false, false, true);
		setOptions9(false, false, false);
		setOptions10(true, false, false, false, false);
        setOptions11(true, true, false);
        setOptions12(false, false, false);
        setOptions13(true, true, true);
	}

    private static void setOptions1(boolean iNumber,
    		boolean iNumberWhole, boolean iNumberDecimal,
    		boolean iInt, boolean iLong, boolean iShort,
    		boolean iFloat, boolean iDouble) {
    	SettingsTokens.setINumber(iNumber);
        SettingsTokens.setINumberWhole(iNumberWhole);
        SettingsTokens.setINumberDecimal(iNumberDecimal);
        SettingsTokens.setIInt(iInt);
        SettingsTokens.setILong(iLong);
        SettingsTokens.setIShort(iShort);
        SettingsTokens.setIFloat(iFloat);
        SettingsTokens.setIDouble(iDouble);
    }

    private static void setOptions2(boolean iClassVariable, boolean iBoolean, boolean iByte) {
    	SettingsTokens.setIClassVariable(iClassVariable);
        SettingsTokens.setIBoolean(iBoolean);
        SettingsTokens.setIByte(iByte);
    }

    private static void setOptions3(boolean iText, boolean iString, boolean iChar) {
    	SettingsTokens.setIText(iText);
        SettingsTokens.setIString(iString);
        SettingsTokens.setIChar(iChar);
    }

    private static void setOptions4(boolean iOperator, boolean iRelation, boolean iAssign, boolean iLogic, boolean iArithmetic, boolean iBitwise) {
    	SettingsTokens.setIOperator(iOperator);
        SettingsTokens.setIRelation(iRelation);
        SettingsTokens.setIAssign(iAssign);
        SettingsTokens.setILogic(iLogic);
        SettingsTokens.setIArithmetic(iArithmetic);
        SettingsTokens.setIBitwise(iBitwise);
    }

    private static void setOptions5(boolean iIfAndElse, boolean iSwitch, boolean iCase, boolean iDefault) {
    	SettingsTokens.setIIfAndElse(iIfAndElse);
        SettingsTokens.setISwitch(iSwitch);
        SettingsTokens.setICase(iCase);
        SettingsTokens.setIDefault(iDefault);
    }

    private static void setOptions6(boolean iLoop, boolean iFor, boolean iWhile, boolean iDo) {
    	SettingsTokens.setILoop(iLoop);
        SettingsTokens.setIFor(iFor);
        SettingsTokens.setIWhile(iWhile);
        SettingsTokens.setIDo(iDo);
    }

    private static void setOptions7(boolean iFunctionDefine, boolean iFunctionUse) {
    	SettingsTokens.setIFunctionDefine(iFunctionDefine);
        SettingsTokens.setIFunctionUse(iFunctionUse);
    }

    private static void setOptions8(boolean iStatic, boolean iFinal, boolean iThrows, boolean iVoid) {
    	SettingsTokens.setIStatic(iStatic);
        SettingsTokens.setIFinal(iFinal);
        SettingsTokens.setIThrows(iThrows);
        SettingsTokens.setIVoid(iVoid);
    }

    private static void setOptions9(boolean iTable, boolean iCast, boolean iGeneric) {
    	SettingsTokens.setITable(iTable);
        SettingsTokens.setICast(iCast);
        SettingsTokens.setIGeneric(iGeneric);
    }

    private static void setOptions10(boolean iClass, boolean iNew, boolean iEnum, boolean iExtends, boolean iImplements) {
    	SettingsTokens.setIClass(iClass);
        SettingsTokens.setINew(iNew);
        SettingsTokens.setIEnum(iEnum);
        SettingsTokens.setIExtends(iExtends);
        SettingsTokens.setIImplements(iImplements);
    }

    private static void setOptions11(boolean iTry, boolean iCatch, boolean iThrow) {
    	SettingsTokens.setITry(iTry);
        SettingsTokens.setICatch(iCatch);
        SettingsTokens.setIThrow(iThrow);
    }

    private static void setOptions12(boolean iBreak, boolean iContinue, boolean iReturn) {
    	SettingsTokens.setIBreak(iBreak);
        SettingsTokens.setIContinue(iContinue);
        SettingsTokens.setIReturn(iReturn);
    }

    private static void setOptions13(boolean iSkipFunctionArgs, boolean iSkipIfArgs, boolean iSkipLoopArgs) {
    	SettingsTokens.setISkipFunctionArgs(iSkipFunctionArgs);
        SettingsTokens.setISkipIfArgs(iSkipIfArgs);
        SettingsTokens.setISkipLoopArgs(iSkipLoopArgs);
    }

}
