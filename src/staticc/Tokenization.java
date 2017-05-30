package staticc;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import model.Project;
import model.Projects;
import model.tokenization.CodeLine;
import model.tokenization.NormalizedCode;
import model.tokenization.Token;
import model.tokenization.TokenFile;
import model.tokenization.TokenLine;
import model.tokenization.TokenProject;
import model.tokenization.TokenProjects;

public final class Tokenization {

    private Tokenization() {

    }

    public static TokenFile tokenization(File file) {
        NormalizedCode normalizedCode = Normalization.codeNormalization(file);
        TokenFile tokenFile = convertTokenFile(normalizedCode, true);

        return tokenFile;
    }

    private static TokenFile convertTokenFile(NormalizedCode normalizedCode, boolean skipUnknown) {
        ArrayList<TokenLine> tokenLines = new ArrayList<>();

        for (CodeLine codeLine : normalizedCode.getCodeLines()) {
            ArrayList<Token> tokens = convertTokenLine(codeLine.getCode());

            if (skipUnknown == true && tokens.get(0) == Token.UNKNOWN_SKIP) {
                continue;
            }

            TokenLine tokenLine = new TokenLine(codeLine.getLineNumber(), tokens);
            tokenLines.add(tokenLine);
        }

        return new TokenFile(normalizedCode.getFile(), tokenLines);
    }

    private static ArrayList<Token> convertTokenLine(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(ifTokenization(line));
        if (SettingsTokens.getISkipIfArgs()) {
            line = removeIfArgs(line);
        }

        tokens.addAll(loopTokenization(line));
        if (SettingsTokens.getISkipLoopArgs()) {
            line = removeLoopArgs(line);
        }

        tokens.addAll(otherBlocksTokenization(line));
        line = removeCatchArgs(line);

        tokens.addAll(castTokenization(line));
        if (SettingsTokens.getISkipFunctionArgs()) {
            line = removeFunctionArgs(line);
        }
        tokens.addAll(functionsTokenization(line));

        tokens.addAll(singleWordsTokenization(line));
        tokens.addAll(operatorsTokenization(line));
        tokens.addAll(classVarTokenization(line));

        tokens.addAll(othersTokenization(line));

        if (tokens.isEmpty()) {
            tokens.add(Token.UNKNOWN_SKIP);
        }

        return tokens;
    }

    private static ArrayList<Token> ifTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "(?<!\\w)else\\{", Token.IF_OR_ELSE, SettingsTokens.getIIfAndElse()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)if\\(", Token.IF_OR_ELSE, SettingsTokens.getIIfAndElse()));
        if (line.equals("else") && SettingsTokens.getIIfAndElse()) {
            tokens.add(Token.IF_OR_ELSE);
        }

        return tokens;
    }

    private static String removeIfArgs(String line) {
        final String IF_ARGS_REGEX = "(^if|^else if)\\(.*\\)";

        while (findRegex(line, IF_ARGS_REGEX)) {
            line = line.replaceAll(IF_ARGS_REGEX, "");
        }

        return line;
    }

    private static ArrayList<Token> loopTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "(?<!\\w)for\\(", Token.LOOP, SettingsTokens.getILoop()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)for\\(", Token.FOR, SettingsTokens.getIFor()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)while\\(", Token.LOOP, SettingsTokens.getILoop()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)while\\(", Token.WHILE, SettingsTokens.getIWhile()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)do\\{", Token.LOOP, SettingsTokens.getILoop()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)do\\{", Token.DO, SettingsTokens.getIDo()));

        return tokens;
    }

    private static String removeLoopArgs(String line) {
        final String LOOP_ARGS_REGEX = "(^for|^while)\\(.*\\)";

        while (findRegex(line, LOOP_ARGS_REGEX)) {
            line = line.replaceAll(LOOP_ARGS_REGEX, "");
        }

        return line;
    }

    private static ArrayList<Token> otherBlocksTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "(?<!\\w)switch\\(", Token.SWITCH, SettingsTokens.getISwitch()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)try\\{", Token.TRY, SettingsTokens.getITry()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)catch\\(", Token.CATCH, SettingsTokens.getICatch()));

        return tokens;
    }

    private static String removeCatchArgs(String line) {
        final String CATCH_ARGS_REGEX = "(^catch)\\(.*\\)";

        while (findRegex(line, CATCH_ARGS_REGEX)) {
            line = line.replaceAll(CATCH_ARGS_REGEX, "");
        }

        return line;
    }

    private static ArrayList<Token> functionsTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        final String FUNCTION_DEF_REGEX = "\\w+\\s\\w+(?<!^if|^else if|^for|^while|^switch|^catch)\\(.*\\).*\\{";
        tokens.addAll(findTokensRegex(line, FUNCTION_DEF_REGEX, Token.FUNCTION_DEF, SettingsTokens.getIFunctionDefine()));
        line = line.replaceAll(FUNCTION_DEF_REGEX, "");

        tokens.addAll(findTokensRegex(line, "(?<!\\w)\\w+(?<!^if|^else if|^for|^while|^switch|^catch)\\(", Token.FUNCTION_USE, SettingsTokens.getIFunctionUse()));

        return tokens;
    }

    private static ArrayList<Token> castTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "[^\\w]\\(\\w+\\)", Token.CAST, SettingsTokens.getICast()));

        return tokens;
    }

    private static String removeFunctionArgs(String line) {
        // Remove functions in function
        final String FUNCTIONS_IN_FUNCTIONS_REGEX = "(?<!^if|^else if|^for|^while|^switch|^catch)\\(([^\\)\\(]*\\([^\\)\\(]*?\\))";
        while (findRegex(line, FUNCTIONS_IN_FUNCTIONS_REGEX)) {
            line = line.replaceAll(FUNCTIONS_IN_FUNCTIONS_REGEX, "(");
        }

        // Remove arguments in function
        line = line.replaceAll("(?<!^if|^else if|^for|^while|^switch|^catch)\\([^\\(]*?\\)", "()");

        return line;
    }

    private static ArrayList<Token> othersTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "\\[\\w*\\]", Token.TABLE, SettingsTokens.getITable()));

        tokens.addAll(findTokensRegex(line, "case.*:", Token.CASE, SettingsTokens.getICase()));
        tokens.addAll(findTokensRegex(line, "default:", Token.DEFAULT, SettingsTokens.getIDefault()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)continue;", Token.CONTINUE, SettingsTokens.getIContinue()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)break;", Token.BREAK, SettingsTokens.getIBreak()));

        return tokens;
    }

    private static ArrayList<Token> singleWordsTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(enum)(?!\\w)", Token.ENUM, SettingsTokens.getIEnum()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(new)(?!\\w)", Token.NEW, SettingsTokens.getINew()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(class)(?!\\w)", Token.CLASS, SettingsTokens.getIClass()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(extends)(?!\\w)", Token.EXTENDS, SettingsTokens.getIExtends()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(implements)(?!\\w)", Token.IMPLEMENTS, SettingsTokens.getIImplements()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(static)(?!\\w)", Token.STATIC, SettingsTokens.getIStatic()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(final)(?!\\w)", Token.FINAL, SettingsTokens.getIFinal()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(throw)(?!\\w)", Token.THROW, SettingsTokens.getIThrow()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(throws)(?!\\w)", Token.THROWS, SettingsTokens.getIThrows()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(void)(?!\\w)", Token.VOID, SettingsTokens.getIVoid()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(return)(?!\\w)", Token.RETURN, SettingsTokens.getIReturn()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(int)(?!\\w)", Token.NUMBER, SettingsTokens.getINumber()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(int)(?!\\w)", Token.NUMBER_WHOLE, SettingsTokens.getINumberWhole()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(int)(?!\\w)", Token.INT, SettingsTokens.getIInt()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(long)(?!\\w)", Token.NUMBER, SettingsTokens.getINumber()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(long)(?!\\w)", Token.NUMBER_WHOLE, SettingsTokens.getINumberWhole()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(long)(?!\\w)", Token.LONG, SettingsTokens.getILong()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(short)(?!\\w)", Token.NUMBER, SettingsTokens.getINumber()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(short)(?!\\w)", Token.NUMBER_WHOLE, SettingsTokens.getINumberWhole()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(short)(?!\\w)", Token.SHORT, SettingsTokens.getIShort()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(float)(?!\\w)", Token.NUMBER, SettingsTokens.getINumber()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(float)(?!\\w)", Token.NUMBER_DEC, SettingsTokens.getINumberDecimal()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(float)(?!\\w)", Token.FLOAT, SettingsTokens.getIFloat()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(double)(?!\\w)", Token.NUMBER, SettingsTokens.getINumber()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(double)(?!\\w)", Token.NUMBER_DEC, SettingsTokens.getINumberDecimal()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(double)(?!\\w)", Token.DOUBLE, SettingsTokens.getIDouble()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(String)(?!\\w)", Token.TEXT, SettingsTokens.getIText()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(String)(?!\\w)", Token.STRING, SettingsTokens.getIString()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(char)(?!\\w)", Token.TEXT, SettingsTokens.getIText()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(char)(?!\\w)", Token.CHAR, SettingsTokens.getIChar()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(boolean)(?!\\w)", Token.BOOLEAN, SettingsTokens.getIBoolean()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(byte)(?!\\w)", Token.BYTE, SettingsTokens.getIByte()));

        return tokens;
    }

    private static ArrayList<Token> classVarTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        line = line.replace("String", "");
        tokens.addAll(findTokensRegex(line, "[A-Z]\\w*\\s\\w+[;=,)]", Token.CLASS_VAR, SettingsTokens.getIClassVariable()));

        return tokens;
    }

    private static ArrayList<Token> operatorsTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();
        // UWAGA: przy zmianie kolejnosci wywolan

        String[] bitwiseList1 = {">>>", ">>", "<<"};
        for (String seqPattern : bitwiseList1) {
        	tokens.addAll(findTokensSequence(line, seqPattern, Token.OPERATOR, SettingsTokens.getIOperator()));
            tokens.addAll(findTokensSequence(line, seqPattern, Token.OP_BITWISE, SettingsTokens.getIAssign()));
            line = line.replace(seqPattern, "");
        }

        // Generic
        tokens.addAll(findTokensRegex(line, "<\\w*>", Token.GENERIC, SettingsTokens.getIGeneric()));
        line = line.replaceAll("<\\w*>", "");

        String[] relationList = {"==", "!=", ">=", "<=", ">", "<"};
        for (String seqPattern : relationList) {
        	tokens.addAll(findTokensSequence(line, seqPattern, Token.OPERATOR, SettingsTokens.getIOperator()));
        	tokens.addAll(findTokensSequence(line, seqPattern, Token.OP_RELATION, SettingsTokens.getIRelation()));
        	line = line.replace(seqPattern, "");
        }

        String[] logicList = {"&&", "||", "!"};
        for (String seqPattern : logicList) {
        	tokens.addAll(findTokensSequence(line, seqPattern, Token.OPERATOR, SettingsTokens.getIOperator()));
        	tokens.addAll(findTokensSequence(line, seqPattern, Token.OP_LOGIC, SettingsTokens.getILogic()));
        	line = line.replace(seqPattern, "");
        }

        // Assign
        tokens.addAll(findTokensSequence(line, "=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("=", "");

        String[] arithmeticList = {"++", "--", "+", "-", "*", "/", "%"};
        for (String seqPatter : arithmeticList) {
        	tokens.addAll(findTokensSequence(line, seqPatter, Token.OPERATOR, SettingsTokens.getIOperator()));
        	tokens.addAll(findTokensSequence(line, seqPatter, Token.OP_ARITHMETIC, SettingsTokens.getIArithmetic()));
        	line = line.replace(seqPatter, "");
        }

        String[] bitwiseList2 = {"&", "|", "^", "~"};
        for (String seqPattern : bitwiseList2) {
        	tokens.addAll(findTokensSequence(line, seqPattern, Token.OPERATOR, SettingsTokens.getIOperator()));
        	tokens.addAll(findTokensSequence(line, seqPattern, Token.OP_BITWISE, SettingsTokens.getIBitwise()));
        	line = line.replace(seqPattern, "");
        }

        return tokens;
    }

    private static ArrayList<Token> findTokensSequence(String str, String charSequence, Token token, boolean checkForTokens) {
        ArrayList<Token> tokens = new ArrayList<>();

        if (checkForTokens == false) {
            return tokens;
        }

        int count = StringUtils.countMatches(str, charSequence);
        for (int i = 0; i < count; i++) {
            tokens.add(token);
        }

        return tokens;
    }

    private static ArrayList<Token> findTokensRegex(String str, String regex, Token token, boolean checkForTokens) {
        ArrayList<Token> tokens = new ArrayList<>();

        if (checkForTokens == false) {
            return tokens;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            tokens.add(token);
        }

        return tokens;
    }

    @SuppressWarnings("unused")
    private static int findRegexCount(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

    private static boolean findRegex(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return true;
        }

        return false;
    }

    public static TokenProject tokenProject(Project project) {
        ArrayList<TokenFile> tokenFiles = new ArrayList<>();

        for (File f : project.getFiles()) {
            TokenFile tokenFile = Tokenization.tokenization(f);
            tokenFiles.add(tokenFile);
        }

        TokenProject tokenProject = new TokenProject(project.getDirectory(), tokenFiles);
        return tokenProject;
    }

    public static TokenProjects tokenProjects(Projects projects) {
        ArrayList<TokenProject> tokenProjectArray = new ArrayList<>();

        for (Project p : projects.getProjects()) {
            TokenProject tokenProject = Tokenization.tokenProject(p);
            tokenProjectArray.add(tokenProject);
        }

        TokenProjects tokenProjects = new TokenProjects(tokenProjectArray);
        return tokenProjects;
    }

    public static String toStringTokenization(Project project) {
        StringBuilder sb = new StringBuilder();

        for (File f : project.getFiles()) {
            NormalizedCode normalizedCode = Normalization.codeNormalization(f);
            TokenFile tokenFile = convertTokenFile(normalizedCode, false);

            ArrayList<CodeLine> codeLines = normalizedCode.getCodeLines();
            ArrayList<TokenLine> tokenLines = tokenFile.getTokenLines();

            sb.append("\tFILE: " + f.getName() + "\n");
            for (int A = 0, B = 0; B < tokenLines.size(); A++, B++) {
                if (codeLines.get(A).getLineNumber() == tokenLines.get(B).getLineNumber()) {
                    String strCode = codeLines.get(A).toString();
                    String strTokens = tokenLines.get(B).getTokens().toString();
                    sb.append(strCode + "\t" + strTokens);
                    sb.append(System.lineSeparator());
                }
                else {
                    B--;
                }
            }
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public static String toStringTokenization(Projects projects) {
        StringBuilder sb = new StringBuilder();

        for (Project p : projects.getProjects()) {
            String str = toStringTokenization(p);
            sb.append(str);
        }

        return sb.toString();
    }
}
