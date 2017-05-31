package utilities.settings;

import java.io.File;

import model.Project;
import model.Projects;
import model.SetttingsTokensRadioGroup;
import staticc.Settings;
import staticc.SettingsTokens;
import utilities.settings.model.SettingsAll;
import utilities.settings.model.SettingsSave;
import utilities.settings.model.SettingsTokensSave;

public class SettingsLoadSave {

	public static void setSettings(File fileProject, File fileBase, int minimalLines, double similarityValue, SetttingsTokensRadioGroup tokenizationType) {
        Project project = new Project(fileProject);
        Projects base = new Projects(fileBase);

        Settings.setProject(project);
        Settings.setBase(base);
        Settings.setMinimalMatchedLinesValue(minimalLines);
        Settings.setMinimalSimilarityAsPercentage(similarityValue);

        if (tokenizationType.equals(SetttingsTokensRadioGroup.NORMAL)) {
            SettingsTokensUtilities.tokenizationTypeNormal();
        }
    }

	public static SettingsAll loadSettings() {
		SettingsSave settingsSave = createSettingsSave();
		SettingsTokensSave settingsTokensSave = createSettingsTokensSave();

		return new SettingsAll(settingsSave, settingsTokensSave);
	}

	public static void saveSettings(SettingsAll settingsAll) {
		saveSettingsSave(settingsAll.settingsSave);
		saveSettingsTokensSave(settingsAll.settingsTokensSave);
	}

	private static SettingsSave createSettingsSave() {
		String project_path = Settings.getProject().getDirectory().getPath();
		String base_path = Settings.getBase().getDirectory().getPath();
		int minimal_matched_lines = Settings.getMinimalMatchedLinesValue();
	 	double minimal_similarity_value = Settings.getMinimalSimilarityAsValue();

		return new SettingsSave(project_path, base_path, minimal_matched_lines, minimal_similarity_value);
	}

	private static void saveSettingsSave(SettingsSave save) {
		Settings.setProject(new Project(new File(save.project_path)));
		Settings.setBase(new Projects(new File(save.base_path)));
		Settings.setMinimalMatchedLinesValue(save.minimal_matched_lines);
		Settings.setMinimalSimilarityAsValue(save.minimal_similarity_value);
	}

	private static SettingsTokensSave createSettingsTokensSave() {
		String i_radio_group = SettingsTokens.getIRadioGroup();

		boolean i_number = SettingsTokens.getINumber();
		boolean i_number_whole = SettingsTokens.getINumberWhole();
		boolean i_number_decimal = SettingsTokens.getINumberDecimal();
		boolean i_int = SettingsTokens.getIInt();
		boolean i_long = SettingsTokens.getILong();
		boolean i_short = SettingsTokens.getIShort();
		boolean i_float = SettingsTokens.getIFloat();
		boolean i_double = SettingsTokens.getIDouble();

		boolean i_class_variable = SettingsTokens.getIClassVariable();
		boolean i_boolean = SettingsTokens.getIBoolean();
		boolean i_byte = SettingsTokens.getIByte();

		boolean i_text = SettingsTokens.getIText();
		boolean i_string = SettingsTokens.getIString();
		boolean i_char = SettingsTokens.getIChar();

		boolean i_operator = SettingsTokens.getIOperator();
		boolean i_relation = SettingsTokens.getIRelation();
		boolean i_assign = SettingsTokens.getIAssign();
		boolean i_logic = SettingsTokens.getILogic();
		boolean i_arithmetic = SettingsTokens.getIArithmetic();
		boolean i_bitwise = SettingsTokens.getIBitwise();

		boolean i_if_and_else = SettingsTokens.getIIfAndElse();
		boolean i_switch = SettingsTokens.getISwitch();
		boolean i_case = SettingsTokens.getICase();
		boolean i_default = SettingsTokens.getIDefault();

		boolean i_loop = SettingsTokens.getILoop();
		boolean i_for = SettingsTokens.getIFor();
		boolean i_while = SettingsTokens.getIWhile();
		boolean i_do = SettingsTokens.getIDo();

		boolean i_function_define = SettingsTokens.getIFunctionDefine();
		boolean i_function_use = SettingsTokens.getIFunctionUse();

		boolean i_static = SettingsTokens.getIStatic();
		boolean i_final = SettingsTokens.getIFinal();
		boolean i_throws = SettingsTokens.getIThrows();
	    boolean i_void = SettingsTokens.getIVoid();

	    boolean i_table = SettingsTokens.getITable();
	    boolean i_cast = SettingsTokens.getICast();
	    boolean i_generic = SettingsTokens.getIGeneric();

	    boolean i_class = SettingsTokens.getIClass();
	    boolean i_new = SettingsTokens.getINew();
	    boolean i_enum = SettingsTokens.getIEnum();
	    boolean i_extends = SettingsTokens.getIExtends();
	    boolean i_implements = SettingsTokens.getIImplements();

	    boolean i_try = SettingsTokens.getITry();
	    boolean i_catch = SettingsTokens.getICatch();
	    boolean i_throw = SettingsTokens.getIThrow();

	    boolean i_break = SettingsTokens.getIBreak();
	    boolean i_continue = SettingsTokens.getIContinue();
	    boolean i_return = SettingsTokens.getIReturn();

	    boolean i_skip_function_args = SettingsTokens.getISkipFunctionArgs();
	    boolean i_skip_if_args = SettingsTokens.getISkipIfArgs();
	    boolean i_skip_loop_args = SettingsTokens.getISkipLoopArgs();

		return new SettingsTokensSave(i_radio_group,
				i_number, i_number_whole, i_number_decimal,
				i_int, i_long, i_short, i_float, i_double,
				i_class_variable, i_boolean, i_byte,
				i_text, i_string, i_char,
				i_operator, i_relation, i_assign, i_logic, i_arithmetic, i_bitwise,
				i_if_and_else, i_switch, i_case, i_default,
				i_loop, i_for, i_while, i_do,
				i_function_define, i_function_use,
				i_static, i_final, i_throws, i_void,
				i_table, i_cast, i_generic,
				i_class, i_new, i_enum, i_extends, i_implements,
				i_try, i_catch, i_throw,
				i_break, i_continue, i_return,
				i_skip_function_args, i_skip_if_args, i_skip_loop_args);
	}

	private static void saveSettingsTokensSave(SettingsTokensSave save) {
		SettingsTokens.setIRadioGroup(SetttingsTokensRadioGroup.valueOf(save.i_radio_group));

		SettingsTokens.setINumber(save.i_number);
		SettingsTokens.setINumberWhole(save.i_number_whole);
		SettingsTokens.setINumberDecimal(save.i_number_decimal);
		SettingsTokens.setIInt(save.i_int);
		SettingsTokens.setILong(save.i_long);
		SettingsTokens.setIShort(save.i_short);
		SettingsTokens.setIFloat(save.i_float);
		SettingsTokens.setIDouble(save.i_double);

		SettingsTokens.setIClassVariable(save.i_class_variable);
		SettingsTokens.setIBoolean(save.i_boolean);
		SettingsTokens.setIByte(save.i_byte);

		SettingsTokens.setIText(save.i_text);
		SettingsTokens.setIString(save.i_string);
		SettingsTokens.setIChar(save.i_char);

		SettingsTokens.setIOperator(save.i_operator);
		SettingsTokens.setIRelation(save.i_relation);
		SettingsTokens.setIAssign(save.i_assign);
		SettingsTokens.setILogic(save.i_logic);
		SettingsTokens.setIArithmetic(save.i_arithmetic);
		SettingsTokens.setIBitwise(save.i_bitwise);

		SettingsTokens.setIIfAndElse(save.i_if_and_else);
		SettingsTokens.setISwitch(save.i_switch);
		SettingsTokens.setICase(save.i_case);
		SettingsTokens.setIDefault(save.i_default);

		SettingsTokens.setILoop(save.i_loop);
		SettingsTokens.setIFor(save.i_for);
		SettingsTokens.setIWhile(save.i_while);
		SettingsTokens.setIDo(save.i_do);

		SettingsTokens.setIFunctionDefine(save.i_function_define);
		SettingsTokens.setIFunctionUse(save.i_function_use);

		SettingsTokens.setIStatic(save.i_static);
		SettingsTokens.setIFinal(save.i_final);
		SettingsTokens.setIThrows(save.i_throws);
	    SettingsTokens.setIVoid(save.i_void);

	    SettingsTokens.setITable(save.i_table);
	    SettingsTokens.setICast(save.i_cast);
	    SettingsTokens.setIGeneric(save.i_generic);

	    SettingsTokens.setIClass(save.i_class);
	    SettingsTokens.setINew(save.i_new);
	    SettingsTokens.setIEnum(save.i_enum);
	    SettingsTokens.setIExtends(save.i_extends);
	    SettingsTokens.setIImplements(save.i_implements);

	    SettingsTokens.setITry(save.i_try);
	    SettingsTokens.setICatch(save.i_catch);
	    SettingsTokens.setIThrow(save.i_throw);

	    SettingsTokens.setIBreak(save.i_break);
	    SettingsTokens.setIContinue(save.i_continue);
	    SettingsTokens.setIReturn(save.i_return);

	    SettingsTokens.setISkipFunctionArgs(save.i_skip_function_args);
	    SettingsTokens.setISkipIfArgs(save.i_skip_if_args);
	    SettingsTokens.setISkipLoopArgs(save.i_skip_loop_args);
	}

}
