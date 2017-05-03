package staticc;

import java.io.File;
import java.util.prefs.Preferences;

import model.Project;
import model.Projects;

public final class Settings {

    private static final Preferences preferences = Preferences.userRoot().node(Settings.class.toString());

    private static final String PROJECT_PATH = "project_path";
    private static final String BASE_PATH = "base_path";
    private static final String MINIMAL_MATCHED_LINES_VALUE = "minimal_matched_lines_value";
    private static final int MINIMAL_MATCHED_LINES_VALUE_DEFAULT = 5;

    private static String I_NUMBER = "i_number", I_NUMBER_WHOLE = "i_number_whole", I_NUMBER_DECIMAL = "i_number_decimal", I_INT = "i_int", I_LONG = "i_long", I_SHORT = "i_short", I_FLOAT = "i_float", I_DOUBLE = "i_double";
    private static String I_CLASS_VARIABLE = "i_class_variable", I_BOOLEAN = "i_boolean", I_BYTE = "i_byte";
    private static String I_TEXT = "i_text", I_STRING = "i_string", I_CHAR = "i_char";
    private static String I_OPERATOR = "i_operator", I_RELATION = "i_relation", I_ASSIGN = "i_assign", I_LOGIC = "i_logic", I_ARITHMETIC = "i_arithmetic", I_BITWISE = "i_bitwise";
    private static String I_IF_AND_ELSE = "i_if_and_else", I_SWITCH = "i_swtich", I_CASE = "i_case", I_DEFAULT = "i_default";
    private static String I_LOOP = "i_loop", I_FOR = "i_for", I_WHILE = "i_while", I_DO = "i_do";
    private static String I_FUNCTION_DEFINE = "i_function_define", I_FUNCTION_USE = "i_function_use", I_CONSTRUCTOR_USE = "i_constructor_use";
    private static String I_STATIC = "i_static", I_FINAL = "i_final", I_THROWS = "i_throws", I_VOID = "i_void";
    private static String I_TABLE = "i_table", I_CAST = "i_cast", I_GENERIC = "i_generic";
    private static String I_CLASS = "i_class", I_NEW = "i_new", I_ENUM = "i_enum", I_EXTENDS = "i_extends", I_IMPLEMETNS = "i_implements";
    private static String I_TRY = "i_try", I_CATCH = "i_catch", I_THROW = "i_throw";
    private static String I_BREAK = "i_break", I_CONTINUE = "i_continue", I_RETURN = "i_return";
    private static String I_SKIP_FUNCTION_ARGS = "i_skip_function_args", I_SKIP_STATMENT_ARGS = "i_skip_statment_args", I_SKIP_LOOP_ARGS = "i_skip_loop_args";

    private Settings() {

    }

    public static Project getProject() {
        return loadProject();
    }

    public static void setProject(Project p) {
        String projectPath = p.getDirectory().getAbsolutePath();
        preferences.put(PROJECT_PATH, projectPath);
    }

    private static Project loadProject() {
        String projectPath = getPreferencePath(PROJECT_PATH);

        if (projectPath == null) {
            return null;
        }
        else {
            File projectDirectory = new File(projectPath);
            Project project = new Project(projectDirectory);
            return project;
        }
    }

    private static String getPreferencePath(final String PATH) {
        String basePath = preferences.get(PATH, null);

        // Sprawdz czy preference jest ustawiony
        if (basePath != null) {
            // Sprawdz czy wskazany plik istnieje
            File fileBase = new File(basePath);
            if (fileBase.exists() == false) {
                preferences.remove(PATH);
                basePath = preferences.get(PATH, null);
            }
        }

        return basePath;
    }

    public static Projects getBase() {
        return loadBase();
    }

    public static void setBase(Projects b) {
        String basePath = b.getDirectory().getAbsolutePath();
        preferences.put(BASE_PATH, basePath);
    }

    private static Projects loadBase() {
        String basePath = getPreferencePath(BASE_PATH);

        if (basePath == null) {
            return null;
        }
        else {
            File baseDirectory = new File(basePath);
            Projects base = new Projects(baseDirectory);
            return base;
        }
    }

    public static int getMinimalMatchedLinesValue() {
        return preferences.getInt(MINIMAL_MATCHED_LINES_VALUE, MINIMAL_MATCHED_LINES_VALUE_DEFAULT);
    }

    public static void setMinimalMatchedLinesValue(int value) {
        preferences.putInt(MINIMAL_MATCHED_LINES_VALUE, value);
    }

    public static boolean getINumber() {
        return preferences.getBoolean(I_NUMBER, false);
    }

    public static void setINumber(boolean value) {
        preferences.putBoolean(I_NUMBER, value);
    }

    public static boolean getINumberWhole() {
        return preferences.getBoolean(I_NUMBER_WHOLE, true);
    }

    public static void setINumberWhole(boolean value) {
        preferences.putBoolean(I_NUMBER_WHOLE, value);
    }

    public static boolean getINumberDecimal() {
        return preferences.getBoolean(I_NUMBER_DECIMAL, true);
    }

    public static void setINumberDecimal(boolean value) {
        preferences.putBoolean(I_NUMBER_DECIMAL, value);
    }

    public static boolean getIInt() {
        return preferences.getBoolean(I_INT, false);
    }

    public static void setIInt(boolean value) {
        preferences.putBoolean(I_INT, value);
    }

    public static boolean getILong() {
        return preferences.getBoolean(I_LONG, false);
    }

    public static void setILong(boolean value) {
        preferences.putBoolean(I_LONG, value);
    }

    public static boolean getIShort() {
        return preferences.getBoolean(I_SHORT, false);
    }

    public static void setIShort(boolean value) {
        preferences.putBoolean(I_SHORT, value);
    }

    public static boolean getIFloat() {
        return preferences.getBoolean(I_FLOAT, false);
    }

    public static void setIFloat(boolean value) {
        preferences.putBoolean(I_FLOAT, value);
    }

    public static boolean getIDouble() {
        return preferences.getBoolean(I_DOUBLE, false);
    }

    public static void setIDouble(boolean value) {
        preferences.putBoolean(I_DOUBLE, value);
    }

    public static boolean getIClassVariable() {
        return preferences.getBoolean(I_CLASS_VARIABLE, true);
    }

    public static void setIClassVariable(boolean value) {
        preferences.putBoolean(I_CLASS_VARIABLE, value);
    }

    public static boolean getIBoolean() {
        return preferences.getBoolean(I_BOOLEAN, true);
    }

    public static void setIBoolean(boolean value) {
        preferences.putBoolean(I_BOOLEAN, value);
    }

    public static boolean getIByte() {
        return preferences.getBoolean(I_BYTE, true);
    }

    public static void setIByte(boolean value) {
        preferences.putBoolean(I_BYTE, value);
    }

    public static boolean getIText() {
        return preferences.getBoolean(I_TEXT, true);
    }

    public static void setIText(boolean value) {
        preferences.putBoolean(I_TEXT, value);
    }

    public static boolean getIString() {
        return preferences.getBoolean(I_STRING, false);
    }

    public static void setIString(boolean value) {
        preferences.putBoolean(I_STRING, value);
    }

    public static boolean getIChar() {
        return preferences.getBoolean(I_CHAR, false);
    }

    public static void setIChar(boolean value) {
        preferences.putBoolean(I_CHAR, value);
    }

    public static boolean getIOperator() {
        return preferences.getBoolean(I_OPERATOR, false);
    }

    public static void setIOperator(boolean value) {
        preferences.putBoolean(I_OPERATOR, value);
    }

    public static boolean getIRelation() {
        return preferences.getBoolean(I_RELATION, true);
    }

    public static void setIRelation(boolean value) {
        preferences.putBoolean(I_RELATION, value);
    }

    public static boolean getIAssign() {
        return preferences.getBoolean(I_ASSIGN, true);
    }

    public static void setIAssign(boolean value) {
        preferences.putBoolean(I_ASSIGN, value);
    }

    public static boolean getILogic() {
        return preferences.getBoolean(I_LOGIC, true);
    }

    public static void setILogic(boolean value) {
        preferences.putBoolean(I_LOGIC, value);
    }

    public static boolean getIArithmetic() {
        return preferences.getBoolean(I_ARITHMETIC, true);
    }

    public static void setIArithmetic(boolean value) {
        preferences.putBoolean(I_ARITHMETIC, value);
    }

    public static boolean getIBitwise() {
        return preferences.getBoolean(I_BITWISE, true);
    }

    public static void setIBitwise(boolean value) {
        preferences.putBoolean(I_BITWISE, value);
    }

    public static boolean getIIfAndElse() {
        return preferences.getBoolean(I_IF_AND_ELSE, true);
    }

    public static void setIIfAndElse(boolean value) {
        preferences.putBoolean(I_IF_AND_ELSE, value);
    }

    public static boolean getISwitch() {
        return preferences.getBoolean(I_SWITCH, true);
    }

    public static void setISwitch(boolean value) {
        preferences.putBoolean(I_SWITCH, value);
    }

    public static boolean getICase() {
        return preferences.getBoolean(I_CASE, true);
    }

    public static void setICase(boolean value) {
        preferences.putBoolean(I_CASE, value);
    }

    public static boolean getIDefault() {
        return preferences.getBoolean(I_DEFAULT, true);
    }

    public static void setIDefault(boolean value) {
        preferences.putBoolean(I_DEFAULT, value);
    }

    public static boolean getILoop() {
        return preferences.getBoolean(I_LOOP, false);
    }

    public static void setILoop(boolean value) {
        preferences.putBoolean(I_LOOP, value);
    }

    public static boolean getIFor() {
        return preferences.getBoolean(I_FOR, true);
    }

    public static void setIFor(boolean value) {
        preferences.putBoolean(I_FOR, value);
    }

    public static boolean getIWhile() {
        return preferences.getBoolean(I_WHILE, true);
    }

    public static void setIWhile(boolean value) {
        preferences.putBoolean(I_WHILE, value);
    }

    public static boolean getIDo() {
        return preferences.getBoolean(I_DO, true);
    }

    public static void setIDo(boolean value) {
        preferences.putBoolean(I_DO, value);
    }

    public static boolean getIFunctionDefine() {
        return preferences.getBoolean(I_FUNCTION_DEFINE, true);
    }

    public static void setIFunctionDefine(boolean value) {
        preferences.putBoolean(I_FUNCTION_DEFINE, value);
    }

    public static boolean getIFunctionUse() {
        return preferences.getBoolean(I_FUNCTION_USE, true);
    }

    public static void setIFunctionUse(boolean value) {
        preferences.putBoolean(I_FUNCTION_USE, value);
    }

    public static boolean getIConstructorUse() {
        return preferences.getBoolean(I_CONSTRUCTOR_USE, true);
    }

    public static void setIConstructorUse(boolean value) {
        preferences.putBoolean(I_CONSTRUCTOR_USE, value);
    }

    public static boolean getIStatic() {
        return preferences.getBoolean(I_STATIC, true);
    }

    public static void setIStatic(boolean value) {
        preferences.putBoolean(I_STATIC, value);
    }

    public static boolean getIFinal() {
        return preferences.getBoolean(I_FINAL, true);
    }

    public static void setIFinal(boolean value) {
        preferences.putBoolean(I_FINAL, value);
    }

    public static boolean getIThrows() {
        return preferences.getBoolean(I_THROWS, true);
    }

    public static void setIThrows(boolean value) {
        preferences.putBoolean(I_THROWS, value);
    }

    public static boolean getIVoid() {
        return preferences.getBoolean(I_VOID, true);
    }

    public static void setIVoid(boolean value) {
        preferences.putBoolean(I_VOID, value);
    }

    public static boolean getITable() {
        return preferences.getBoolean(I_TABLE, true);
    }

    public static void setITable(boolean value) {
        preferences.putBoolean(I_TABLE, value);
    }

    public static boolean getICast() {
        return preferences.getBoolean(I_CAST, true);
    }

    public static void setICast(boolean value) {
        preferences.putBoolean(I_CAST, value);
    }

    public static boolean getIGeneric() {
        return preferences.getBoolean(I_GENERIC, true);
    }

    public static void setIGeneric(boolean value) {
        preferences.putBoolean(I_GENERIC, value);
    }

    public static boolean getIClass() {
        return preferences.getBoolean(I_CLASS, true);
    }

    public static void setIClass(boolean value) {
        preferences.putBoolean(I_CLASS, value);
    }

    public static boolean getINew() {
        return preferences.getBoolean(I_NEW, true);
    }

    public static void setINew(boolean value) {
        preferences.putBoolean(I_NEW, value);
    }

    public static boolean getIEnum() {
        return preferences.getBoolean(I_ENUM, true);
    }

    public static void setIEnum(boolean value) {
        preferences.putBoolean(I_ENUM, value);
    }

    public static boolean getIExtends() {
        return preferences.getBoolean(I_EXTENDS, true);
    }

    public static void setIExtends(boolean value) {
        preferences.putBoolean(I_EXTENDS, value);
    }

    public static boolean getIImplements() {
        return preferences.getBoolean(I_IMPLEMETNS, true);
    }

    public static void setIImplements(boolean value) {
        preferences.putBoolean(I_IMPLEMETNS, value);
    }

    public static boolean getITry() {
        return preferences.getBoolean(I_TRY, true);
    }

    public static void setITry(boolean value) {
        preferences.putBoolean(I_TRY, value);
    }

    public static boolean getICatch() {
        return preferences.getBoolean(I_CATCH, true);
    }

    public static void setICatch(boolean value) {
        preferences.putBoolean(I_CATCH, value);
    }

    public static boolean getIThrow() {
        return preferences.getBoolean(I_THROW, true);
    }

    public static void setIThrow(boolean value) {
        preferences.putBoolean(I_THROW, value);
    }

    public static boolean getIBreak() {
        return preferences.getBoolean(I_BREAK, true);
    }

    public static void setIBreak(boolean value) {
        preferences.putBoolean(I_BREAK, value);
    }

    public static boolean getIContinue() {
        return preferences.getBoolean(I_CONTINUE, true);
    }

    public static void setIContinue(boolean value) {
        preferences.putBoolean(I_CONTINUE, value);
    }

    public static boolean getIReturn() {
        return preferences.getBoolean(I_RETURN, true);
    }

    public static void setIReturn(boolean value) {
        preferences.putBoolean(I_RETURN, value);
    }

    public static boolean getISkipFunctionArgs() {
        return preferences.getBoolean(I_SKIP_FUNCTION_ARGS, false);
    }

    public static void setISkipFunctionArgs(boolean value) {
        preferences.putBoolean(I_SKIP_FUNCTION_ARGS, value);
    }

    public static boolean getISkipStatmentArgs() {
        return preferences.getBoolean(I_SKIP_STATMENT_ARGS, true);
    }

    public static void setISkipStatmentArgs(boolean value) {
        preferences.putBoolean(I_SKIP_STATMENT_ARGS, value);
    }

    public static boolean getISkipLoopArgs() {
        return preferences.getBoolean(I_SKIP_LOOP_ARGS, true);
    }

    public static void setISkipLoopArgs(boolean value) {
        preferences.putBoolean(I_SKIP_LOOP_ARGS, value);
    }

}
