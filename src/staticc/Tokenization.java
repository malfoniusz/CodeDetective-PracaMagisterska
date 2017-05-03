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

    static final boolean SKIP_ARG_IN_STATEMENTS_AND_LOOPS = true;

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

            if (skipUnknown == true && tokens.get(0) == Token.UNKNOWN) {
                continue;
            }

            TokenLine tokenLine = new TokenLine(codeLine.getLineNumber(), tokens);
            tokenLines.add(tokenLine);
        }

        return new TokenFile(normalizedCode.getFile(), tokenLines);
    }

    private static ArrayList<Token> convertTokenLine(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        boolean newTokens = tokens.addAll(compoundStatmentsTokenization(line));

        if (SKIP_ARG_IN_STATEMENTS_AND_LOOPS && newTokens) {
            return tokens;
        }

        tokens.addAll(functionsTokenization(line, newTokens));

        tokens.addAll(singleWordsTokenization(line));
        tokens.addAll(operatorsTokenization(line));
        tokens.addAll(classVarTokenization(line));

        tokens.addAll(othersTokenization(line));

        if (tokens.isEmpty()) {
            tokens.add(Token.UNKNOWN);
        }

        return tokens;
    }

    private static ArrayList<Token> functionsTokenization(String line, boolean newTokens) {
        ArrayList<Token> tokens = new ArrayList<>();

        if (newTokens == false) {
            boolean functionDef = tokens.addAll(findTokensRegex(line, "(?<!\\w)\\w+\\(.*\\).*\\{", Token.FUNCTION_DEF));

            if (functionDef == false) {
                tokens.addAll(findTokensRegex(line, "(?<!\\w)[a-z]\\w*\\(", Token.FUNCTION_USE));
                tokens.addAll(findTokensRegex(line, "(?<!\\w)[A-Z]\\w*\\(", Token.CONSTRUCTOR_USE));

                if (tokens.isEmpty() == false) {
                    tokens.addAll(functionArguments(line));
                }
            }
        }

        return tokens;
    }

    private static ArrayList<Token> functionArguments(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        boolean noArgs = findRegex(line, "(?<!\\w)\\w*\\(\\)[;{]");
        if (noArgs) {
            return tokens;
        }

        int numberOfArgs = findRegexCount(line, ",");
        numberOfArgs += 1;
        for (int i = 0; i < numberOfArgs; i++) {
            tokens.add(Token.ARG);
        }

        return tokens;
    }

    private static ArrayList<Token> othersTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "\\[\\w*\\]", Token.TABLE));

        tokens.addAll(findTokensRegex(line, "case.*:", Token.CASE, SettingsTokens.getICase()));
        tokens.addAll(findTokensRegex(line, "default:", Token.DEFAULT, SettingsTokens.getIDefault()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)continue;", Token.CONTINUE));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)break;", Token.BREAK));

        tokens.addAll(findTokensRegex(line, "[^\\w]\\(\\w+\\)", Token.CAST));

        return tokens;
    }

    private static ArrayList<Token> singleWordsTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(enum)(?!\\w)", Token.ENUM));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(new)(?!\\w)", Token.NEW));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(class)(?!\\w)", Token.CLASS));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(extends)(?!\\w)", Token.EXTENDS));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(implements)(?!\\w)", Token.IMPLEMENTS));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(static)(?!\\w)", Token.STATIC));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(final)(?!\\w)", Token.FINAL));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(throw)(?!\\w)", Token.THROW));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(throws)(?!\\w)", Token.THROWS));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(void)(?!\\w)", Token.VOID));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(return)(?!\\w)", Token.RETURN));

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

    private static ArrayList<Token> compoundStatmentsTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "(?<!\\w)else\\{", Token.IF_OR_ELSE, SettingsTokens.getIIfAndElse()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)if\\(", Token.IF_OR_ELSE, SettingsTokens.getIIfAndElse()));
        if (line.equals("else") && SettingsTokens.getIIfAndElse()) {
            tokens.add(Token.IF_OR_ELSE);
        }

        tokens.addAll(findTokensRegex(line, "(?<!\\w)for\\(", Token.LOOP, SettingsTokens.getILoop()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)for\\(", Token.FOR, SettingsTokens.getIFor()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)while\\(", Token.LOOP, SettingsTokens.getILoop()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)while\\(", Token.WHILE, SettingsTokens.getIWhile()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)do\\{", Token.LOOP, SettingsTokens.getILoop()));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)do\\{", Token.DO, SettingsTokens.getIDo()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)switch\\(", Token.SWITCH, SettingsTokens.getISwitch()));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)try\\{", Token.TRY));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)catch\\(", Token.CATCH));

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
        // Assign 1

        tokens.addAll(findTokensSequence(line, "<<=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "<<=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("<<=", " ");
        tokens.addAll(findTokensSequence(line, ">>=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, ">>=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace(">>=", " ");
        // Bitwise 1
        tokens.addAll(findTokensSequence(line, "<<", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "<<", Token.OP_BITWISE, SettingsTokens.getIBitwise()));
        line = line.replace("<<", " ");
        tokens.addAll(findTokensSequence(line, ">>>", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, ">>>", Token.OP_BITWISE, SettingsTokens.getIBitwise()));
        line = line.replace(">>>", " ");
        tokens.addAll(findTokensSequence(line, ">>", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, ">>", Token.OP_BITWISE, SettingsTokens.getIBitwise()));
        line = line.replace(">>", " ");

        // Generic
        tokens.addAll(findTokensRegex(line, "<\\w*>", Token.GENERIC));
        line = line.replaceAll("<\\w*>", "");

        // Relation
        tokens.addAll(findTokensSequence(line, "==", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "==", Token.OP_RELATION, SettingsTokens.getIRelation()));
        line = line.replace("==", " ");
        tokens.addAll(findTokensSequence(line, "!=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "!=", Token.OP_RELATION, SettingsTokens.getIRelation()));
        line = line.replace("!=", " ");
        tokens.addAll(findTokensSequence(line, ">=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, ">=", Token.OP_RELATION, SettingsTokens.getIRelation()));
        line = line.replace(">=", " ");
        tokens.addAll(findTokensSequence(line, "<=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "<=", Token.OP_RELATION, SettingsTokens.getIRelation()));
        line = line.replace("<=", " ");
        tokens.addAll(findTokensSequence(line, ">", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, ">", Token.OP_RELATION, SettingsTokens.getIRelation()));
        line = line.replace(">", " ");
        tokens.addAll(findTokensSequence(line, "<", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "<", Token.OP_RELATION, SettingsTokens.getIRelation()));
        line = line.replace("<", " ");

        // Logic
        tokens.addAll(findTokensSequence(line, "&&", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "&&", Token.OP_LOGIC, SettingsTokens.getILogic()));
        line = line.replace("&&", " ");
        tokens.addAll(findTokensSequence(line, "||", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "||", Token.OP_LOGIC, SettingsTokens.getILogic()));
        line = line.replace("||", " ");
        tokens.addAll(findTokensSequence(line, "!", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "!", Token.OP_LOGIC, SettingsTokens.getILogic()));
        line = line.replace("!", " ");

        // Assign 2
        tokens.addAll(findTokensSequence(line, "+=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "+=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("+=", " ");
        tokens.addAll(findTokensSequence(line, "-=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "-=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("-=", " ");
        tokens.addAll(findTokensSequence(line, "*=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "*=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("*=", " ");
        tokens.addAll(findTokensSequence(line, "/=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "/=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("/=", " ");
        tokens.addAll(findTokensSequence(line, "%=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "%=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("%=", " ");
        tokens.addAll(findTokensSequence(line, "&=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "&=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("&=", " ");
        tokens.addAll(findTokensSequence(line, "^=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "^=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("^=", " ");
        tokens.addAll(findTokensSequence(line, "|=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "|=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("|=", " ");
        tokens.addAll(findTokensSequence(line, "=", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "=", Token.OP_ASSIGN, SettingsTokens.getIAssign()));
        line = line.replace("=", " ");

        // Arithmetic
        tokens.addAll(findTokensSequence(line, "++", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "++", Token.OP_ARITHMETIC, SettingsTokens.getIArithmetic()));
        line = line.replace("++", " ");
        tokens.addAll(findTokensSequence(line, "--", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "--", Token.OP_ARITHMETIC, SettingsTokens.getIArithmetic()));
        line = line.replace("--", " ");
        tokens.addAll(findTokensSequence(line, "+", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "+", Token.OP_ARITHMETIC, SettingsTokens.getIArithmetic()));
        line = line.replace("+", " ");
        tokens.addAll(findTokensSequence(line, "-", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "-", Token.OP_ARITHMETIC, SettingsTokens.getIArithmetic()));
        line = line.replace("-", " ");
        tokens.addAll(findTokensSequence(line, "*", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "*", Token.OP_ARITHMETIC, SettingsTokens.getIArithmetic()));
        line = line.replace("*", " ");
        tokens.addAll(findTokensSequence(line, "/", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "/", Token.OP_ARITHMETIC, SettingsTokens.getIArithmetic()));
        line = line.replace("/", " ");
        tokens.addAll(findTokensSequence(line, "%", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "%", Token.OP_ARITHMETIC, SettingsTokens.getIArithmetic()));
        line = line.replace("%", " ");

        // Bitwise 2
        tokens.addAll(findTokensSequence(line, "&", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "&", Token.OP_BITWISE, SettingsTokens.getIBitwise()));
        line = line.replace("&", " ");
        tokens.addAll(findTokensSequence(line, "|", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "|", Token.OP_BITWISE, SettingsTokens.getIBitwise()));
        line = line.replace("|", " ");
        tokens.addAll(findTokensSequence(line, "^", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "^", Token.OP_BITWISE, SettingsTokens.getIBitwise()));
        line = line.replace("^", " ");
        tokens.addAll(findTokensSequence(line, "~", Token.OPERATOR, SettingsTokens.getIOperator()));
        tokens.addAll(findTokensSequence(line, "~", Token.OP_BITWISE, SettingsTokens.getIBitwise()));
        line = line.replace("~", " ");

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

    // TODO: usun
    private static ArrayList<Token> findTokensRegex(String str, String regex, Token token) {
        ArrayList<Token> tokens = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
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
