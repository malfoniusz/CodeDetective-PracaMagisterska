package staticc;

import java.util.prefs.Preferences;

import model.SetttingsTokensRadioGroup;

public class SettingsTokens {

    private static final Preferences preferences = Preferences.userRoot().node(SettingsTokens.class.toString());

    private static final String I_RADIO_GROUP = "i_radio_group";
    private static final String I_NUMBER = "i_number", I_NUMBER_WHOLE = "i_number_whole", I_NUMBER_DECIMAL = "i_number_decimal", I_INT = "i_int", I_LONG = "i_long", I_SHORT = "i_short", I_FLOAT = "i_float", I_DOUBLE = "i_double";
    private static final String I_CLASS_VARIABLE = "i_class_variable", I_BOOLEAN = "i_boolean", I_BYTE = "i_byte";
    private static final String I_TEXT = "i_text", I_STRING = "i_string", I_CHAR = "i_char";
    private static final String I_OPERATOR = "i_operator", I_RELATION = "i_relation", I_ASSIGN = "i_assign", I_LOGIC = "i_logic", I_ARITHMETIC = "i_arithmetic", I_BITWISE = "i_bitwise";
    private static final String I_IF_AND_ELSE = "i_if_and_else", I_SWITCH = "i_switch", I_CASE = "i_case", I_DEFAULT = "i_default";
    private static final String I_LOOP = "i_loop", I_FOR = "i_for", I_WHILE = "i_while", I_DO = "i_do";
    private static final String I_FUNCTION_DEFINE = "i_function_define", I_FUNCTION_USE = "i_function_use";
    private static final String I_STATIC = "i_static", I_FINAL = "i_final", I_THROWS = "i_throws", I_VOID = "i_void";
    private static final String I_TABLE = "i_table", I_CAST = "i_cast", I_GENERIC = "i_generic";
    private static final String I_CLASS = "i_class", I_NEW = "i_new", I_ENUM = "i_enum", I_EXTENDS = "i_extends", I_IMPLEMENTS = "i_implements";
    private static final String I_TRY = "i_try", I_CATCH = "i_catch", I_THROW = "i_throw";
    private static final String I_BREAK = "i_break", I_CONTINUE = "i_continue", I_RETURN = "i_return";
    private static final String I_SKIP_FUNCTION_ARGS = "i_skip_function_args", I_SKIP_IF_ARGS = "i_skip_if_args", I_SKIP_LOOP_ARGS = "i_skip_loop_args";

    private static String i_radio_group = preferences.get(I_RADIO_GROUP, SetttingsTokensRadioGroup.CUSTOM.toString());

    private static boolean i_number = preferences.getBoolean(I_NUMBER, false);
    private static boolean i_number_whole = preferences.getBoolean(I_NUMBER_WHOLE, true);
    private static boolean i_number_decimal = preferences.getBoolean(I_NUMBER_DECIMAL, true);
    private static boolean i_int = preferences.getBoolean(I_INT, false);
    private static boolean i_long = preferences.getBoolean(I_LONG, false);
    private static boolean i_short = preferences.getBoolean(I_SHORT, false);
    private static boolean i_float = preferences.getBoolean(I_FLOAT, false);
    private static boolean i_double = preferences.getBoolean(I_DOUBLE, false);

    private static boolean i_class_variable = preferences.getBoolean(I_CLASS_VARIABLE, false);
    private static boolean i_boolean = preferences.getBoolean(I_BOOLEAN, true);
    private static boolean i_byte = preferences.getBoolean(I_BYTE, true);

    private static boolean i_text = preferences.getBoolean(I_TEXT, true);
    private static boolean i_string = preferences.getBoolean(I_STRING, false);
    private static boolean i_char = preferences.getBoolean(I_CHAR, false);

    private static boolean i_operator = preferences.getBoolean(I_OPERATOR, false);
    private static boolean i_relation = preferences.getBoolean(I_RELATION, true);
    private static boolean i_assign = preferences.getBoolean(I_ASSIGN, false);
    private static boolean i_logic = preferences.getBoolean(I_LOGIC, true);
    private static boolean i_arithmetic = preferences.getBoolean(I_ARITHMETIC, true);
    private static boolean i_bitwise = preferences.getBoolean(I_BITWISE, true);

    private static boolean i_if_and_else = preferences.getBoolean(I_IF_AND_ELSE, true);
    private static boolean i_switch = preferences.getBoolean(I_SWITCH, true);
    private static boolean i_case = preferences.getBoolean(I_CASE, true);
    private static boolean i_default = preferences.getBoolean(I_DEFAULT, true);

    private static boolean i_loop = preferences.getBoolean(I_LOOP, false);
    private static boolean i_for = preferences.getBoolean(I_FOR, true);
    private static boolean i_while = preferences.getBoolean(I_WHILE, true);
    private static boolean i_do = preferences.getBoolean(I_DO, true);

    private static boolean i_function_define = preferences.getBoolean(I_FUNCTION_DEFINE, true);
    private static boolean i_function_use = preferences.getBoolean(I_FUNCTION_USE, false);

    private static boolean i_static = preferences.getBoolean(I_STATIC, true);
    private static boolean i_final = preferences.getBoolean(I_FINAL, true);
    private static boolean i_throws = preferences.getBoolean(I_THROWS, true);
    private static boolean i_void = preferences.getBoolean(I_VOID, true);

    private static boolean i_table = preferences.getBoolean(I_TABLE, true);
    private static boolean i_cast = preferences.getBoolean(I_CAST, true);
    private static boolean i_generic = preferences.getBoolean(I_GENERIC, true);

    private static boolean i_class = preferences.getBoolean(I_CLASS, true);
    private static boolean i_new = preferences.getBoolean(I_NEW, false);
    private static boolean i_enum = preferences.getBoolean(I_ENUM, true);
    private static boolean i_extends = preferences.getBoolean(I_EXTENDS, true);
    private static boolean i_implements = preferences.getBoolean(I_IMPLEMENTS, true);

    private static boolean i_try = preferences.getBoolean(I_TRY, true);
    private static boolean i_catch = preferences.getBoolean(I_CATCH, true);
    private static boolean i_throw = preferences.getBoolean(I_THROW, true);

    private static boolean i_break = preferences.getBoolean(I_BREAK, true);
    private static boolean i_continue = preferences.getBoolean(I_CONTINUE, true);
    private static boolean i_return = preferences.getBoolean(I_RETURN, true);

    private static boolean i_skip_function_args = preferences.getBoolean(I_SKIP_FUNCTION_ARGS, false);
    private static boolean i_skip_if_args = preferences.getBoolean(I_SKIP_IF_ARGS, true);
    private static boolean i_skip_loop_args = preferences.getBoolean(I_SKIP_LOOP_ARGS, true);

    private SettingsTokens() {

    }

    public static String getIRadioGroup() {
        // Z nieznanych powodow wartosc domyslna jest zawsze zwracana malymi literami
        return i_radio_group.toUpperCase();
    }

    public static void setIRadioGroup(SetttingsTokensRadioGroup radioGroup) {
        i_radio_group = radioGroup.toString();
        preferences.put(I_RADIO_GROUP, radioGroup.toString());
    }

    public static boolean getINumber() {
        return i_number;
    }

    public static void setINumber(boolean value) {
        i_number = value;
        preferences.putBoolean(I_NUMBER, value);
    }

    public static boolean getINumberWhole() {
        return i_number_whole;
    }

    public static void setINumberWhole(boolean value) {
        i_number_whole = value;
        preferences.putBoolean(I_NUMBER_WHOLE, value);
    }

    public static boolean getINumberDecimal() {
        return i_number_decimal;
    }

    public static void setINumberDecimal(boolean value) {
        i_number_decimal = value;
        preferences.putBoolean(I_NUMBER_DECIMAL, value);
    }

    public static boolean getIInt() {
        return i_int;
    }

    public static void setIInt(boolean value) {
        i_int = value;
        preferences.putBoolean(I_INT, value);
    }

    public static boolean getILong() {
        return i_long;
    }

    public static void setILong(boolean value) {
        i_long = value;
        preferences.putBoolean(I_LONG, value);
    }

    public static boolean getIShort() {
        return i_short;
    }

    public static void setIShort(boolean value) {
        i_short = value;
        preferences.putBoolean(I_SHORT, value);
    }

    public static boolean getIFloat() {
        return i_float;
    }

    public static void setIFloat(boolean value) {
        i_float = value;
        preferences.putBoolean(I_FLOAT, value);
    }

    public static boolean getIDouble() {
        return i_double;
    }

    public static void setIDouble(boolean value) {
        i_double = value;
        preferences.putBoolean(I_DOUBLE, value);
    }

    public static boolean getIClassVariable() {
        return i_class_variable;
    }

    public static void setIClassVariable(boolean value) {
        i_class_variable = value;
        preferences.putBoolean(I_CLASS_VARIABLE, value);
    }

    public static boolean getIBoolean() {
        return i_boolean;
    }

    public static void setIBoolean(boolean value) {
        i_boolean = value;
        preferences.putBoolean(I_BOOLEAN, value);
    }

    public static boolean getIByte() {
        return i_byte;
    }

    public static void setIByte(boolean value) {
        i_byte = value;
        preferences.putBoolean(I_BYTE, value);
    }

    public static boolean getIText() {
        return i_text;
    }

    public static void setIText(boolean value) {
        i_text = value;
        preferences.putBoolean(I_TEXT, value);
    }

    public static boolean getIString() {
        return i_string;
    }

    public static void setIString(boolean value) {
        i_string = value;
        preferences.putBoolean(I_STRING, value);
    }

    public static boolean getIChar() {
        return i_char;
    }

    public static void setIChar(boolean value) {
        i_char = value;
        preferences.putBoolean(I_CHAR, value);
    }

    public static boolean getIOperator() {
        return i_operator;
    }

    public static void setIOperator(boolean value) {
        i_operator = value;
        preferences.putBoolean(I_OPERATOR, value);
    }

    public static boolean getIRelation() {
        return i_relation;
    }

    public static void setIRelation(boolean value) {
        i_relation = value;
        preferences.putBoolean(I_RELATION, value);
    }

    public static boolean getIAssign() {
        return i_assign;
    }

    public static void setIAssign(boolean value) {
        i_assign = value;
        preferences.putBoolean(I_ASSIGN, value);
    }

    public static boolean getILogic() {
        return i_logic;
    }

    public static void setILogic(boolean value) {
        i_logic = value;
        preferences.putBoolean(I_LOGIC, value);
    }

    public static boolean getIArithmetic() {
        return i_arithmetic;
    }

    public static void setIArithmetic(boolean value) {
        i_arithmetic = value;
        preferences.putBoolean(I_ARITHMETIC, value);
    }

    public static boolean getIBitwise() {
        return i_bitwise;
    }

    public static void setIBitwise(boolean value) {
        i_bitwise = value;
        preferences.putBoolean(I_BITWISE, value);
    }

    public static boolean getIIfAndElse() {
        return i_if_and_else;
    }

    public static void setIIfAndElse(boolean value) {
        i_if_and_else = value;
        preferences.putBoolean(I_IF_AND_ELSE, value);
    }

    public static boolean getISwitch() {
        return i_switch;
    }

    public static void setISwitch(boolean value) {
        i_switch = value;
        preferences.putBoolean(I_SWITCH, value);
    }

    public static boolean getICase() {
        return i_case;
    }

    public static void setICase(boolean value) {
        i_case = value;
        preferences.putBoolean(I_CASE, value);
    }

    public static boolean getIDefault() {
        return i_default;
    }

    public static void setIDefault(boolean value) {
        i_default = value;
        preferences.putBoolean(I_DEFAULT, value);
    }

    public static boolean getILoop() {
        return i_loop;
    }

    public static void setILoop(boolean value) {
        i_loop = value;
        preferences.putBoolean(I_LOOP, value);
    }

    public static boolean getIFor() {
        return i_for;
    }

    public static void setIFor(boolean value) {
        i_for = value;
        preferences.putBoolean(I_FOR, value);
    }

    public static boolean getIWhile() {
        return i_while;
    }

    public static void setIWhile(boolean value) {
        i_while = value;
        preferences.putBoolean(I_WHILE, value);
    }

    public static boolean getIDo() {
        return i_do;
    }

    public static void setIDo(boolean value) {
        i_do = value;
        preferences.putBoolean(I_DO, value);
    }

    public static boolean getIFunctionDefine() {
        return i_function_define;
    }

    public static void setIFunctionDefine(boolean value) {
        i_function_define = value;
        preferences.putBoolean(I_FUNCTION_DEFINE, value);
    }

    public static boolean getIFunctionUse() {
        return i_function_use;
    }

    public static void setIFunctionUse(boolean value) {
        i_function_use = value;
        preferences.putBoolean(I_FUNCTION_USE, value);
    }

    public static boolean getIStatic() {
        return i_static;
    }

    public static void setIStatic(boolean value) {
        i_static = value;
        preferences.putBoolean(I_STATIC, value);
    }

    public static boolean getIFinal() {
        return i_final;
    }

    public static void setIFinal(boolean value) {
        i_final = value;
        preferences.putBoolean(I_FINAL, value);
    }

    public static boolean getIThrows() {
        return i_throws;
    }

    public static void setIThrows(boolean value) {
        i_throws = value;
        preferences.putBoolean(I_THROWS, value);
    }

    public static boolean getIVoid() {
        return i_void;
    }

    public static void setIVoid(boolean value) {
        i_void = value;
        preferences.putBoolean(I_VOID, value);
    }

    public static boolean getITable() {
        return i_table;
    }

    public static void setITable(boolean value) {
        i_table = value;
        preferences.putBoolean(I_TABLE, value);
    }

    public static boolean getICast() {
        return i_cast;
    }

    public static void setICast(boolean value) {
        i_cast = value;
        preferences.putBoolean(I_CAST, value);
    }

    public static boolean getIGeneric() {
        return i_generic;
    }

    public static void setIGeneric(boolean value) {
        i_generic = value;
        preferences.putBoolean(I_GENERIC, value);
    }

    public static boolean getIClass() {
        return i_class;
    }

    public static void setIClass(boolean value) {
        i_class = value;
        preferences.putBoolean(I_CLASS, value);
    }

    public static boolean getINew() {
        return i_new;
    }

    public static void setINew(boolean value) {
        i_new = value;
        preferences.putBoolean(I_NEW, value);
    }

    public static boolean getIEnum() {
        return i_enum;
    }

    public static void setIEnum(boolean value) {
        i_enum = value;
        preferences.putBoolean(I_ENUM, value);
    }

    public static boolean getIExtends() {
        return i_extends;
    }

    public static void setIExtends(boolean value) {
        i_extends = value;
        preferences.putBoolean(I_EXTENDS, value);
    }

    public static boolean getIImplements() {
        return i_implements;
    }

    public static void setIImplements(boolean value) {
        i_implements = value;
        preferences.putBoolean(I_IMPLEMENTS, value);
    }

    public static boolean getITry() {
        return i_try;
    }

    public static void setITry(boolean value) {
        i_try = value;
        preferences.putBoolean(I_TRY, value);
    }

    public static boolean getICatch() {
        return i_catch;
    }

    public static void setICatch(boolean value) {
        i_catch = value;
        preferences.putBoolean(I_CATCH, value);
    }

    public static boolean getIThrow() {
        return i_throw;
    }

    public static void setIThrow(boolean value) {
        i_throw = value;
        preferences.putBoolean(I_THROW, value);
    }

    public static boolean getIBreak() {
        return i_break;
    }

    public static void setIBreak(boolean value) {
        i_break = value;
        preferences.putBoolean(I_BREAK, value);
    }

    public static boolean getIContinue() {
        return i_continue;
    }

    public static void setIContinue(boolean value) {
        i_continue = value;
        preferences.putBoolean(I_CONTINUE, value);
    }

    public static boolean getIReturn() {
        return i_return;
    }

    public static void setIReturn(boolean value) {
        i_return = value;
        preferences.putBoolean(I_RETURN, value);
    }

    public static boolean getISkipFunctionArgs() {
        return i_skip_function_args;
    }

    public static void setISkipFunctionArgs(boolean value) {
        i_skip_function_args = value;
        preferences.putBoolean(I_SKIP_FUNCTION_ARGS, value);
    }

    public static boolean getISkipIfArgs() {
        return i_skip_if_args;
    }

    public static void setISkipIfArgs(boolean value) {
        i_skip_if_args = value;
        preferences.putBoolean(I_SKIP_IF_ARGS, value);
    }

    public static boolean getISkipLoopArgs() {
        return i_skip_loop_args;
    }

    public static void setISkipLoopArgs(boolean value) {
        i_skip_loop_args = value;
        preferences.putBoolean(I_SKIP_LOOP_ARGS, value);
    }

}
