package staticc;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import model.Project;
import model.Projects;
import model.tokenization.CodeFile;
import model.tokenization.CodeLine;
import model.tokenization.Token;
import model.tokenization.TokenFile;
import model.tokenization.TokenLine;
import model.tokenization.TokenProject;
import model.tokenization.TokenProjects;

public final class Tokenization {

    final static boolean SKIP_ARG_IN_COMPOUND_STATEMENTS = true;

    public static TokenFile tokenization(File file) {
        CodeFile normalizedCode = Normalization.codeNormalization(file);
        TokenFile tokenFile = convertTokenFile(normalizedCode);

        return tokenFile;
    }

    private static TokenFile convertTokenFile(CodeFile codeFile) {
        ArrayList<TokenLine> tokenLines = new ArrayList<>();

        for (CodeLine codeLine : codeFile.getCodeLines()) {
            ArrayList<Token> tokens = convertTokenLine(codeLine.getCode());
            TokenLine tokenLine = new TokenLine(codeLine.getLineNumber(), tokens);
            tokenLines.add(tokenLine);
        }

        return new TokenFile(codeFile.getFile(), tokenLines);
    }

    private static ArrayList<Token> convertTokenLine(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        boolean newTokens = tokens.addAll(compoundStatmentsTokenization(line));

        if (SKIP_ARG_IN_COMPOUND_STATEMENTS && newTokens) {
            return tokens;
        }

        tokens.addAll(functionsTokenization(line, newTokens));

        tokens.addAll(singleWordsTokenization(line));
        tokens.addAll(operatorsTokenization(line));
        tokens.addAll(classVarTokenization(line, tokens));

        tokens.addAll(othersTokenization(line));

        if (tokens.isEmpty()) {
            tokens.add(Token.UNKNOWN);
        }

        return tokens;
    }

    private static ArrayList<Token> functionsTokenization(String line, boolean newTokens) {
        ArrayList<Token> tokens = new ArrayList<>();

        if (newTokens == false) {
            boolean foundTokens = tokens.addAll(findTokensRegex(line, "(?<!\\w)\\w+\\(.*\\).*\\{", Token.FUNCTION_DEF));

            if (foundTokens == false) {
                tokens.addAll(findTokensRegex(line, "(?<!\\w)[a-z]\\w*\\(", Token.FUNCTION_USE));
                tokens.addAll(findTokensRegex(line, "(?<!\\w)[A-Z]\\w*\\(", Token.CONSTRUCTOR_USE));
            }
        }

        return tokens;
    }

    private static ArrayList<Token> othersTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "\\[\\w*\\]", Token.TABLE));

        tokens.addAll(findTokensRegex(line, "case.*:", Token.CASE));
        tokens.addAll(findTokensSequence(line, "default:", Token.DEFAULT));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)continue;", Token.CONTINUE));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)break;", Token.BREAK));

        tokens.addAll(findTokensRegex(line, "[^\\w]\\(\\w+\\)", Token.CASTING));

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
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(throw|throws)(?!\\w)", Token.THROW));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(void)(?!\\w)", Token.VOID));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(return)(?!\\w)", Token.RETURN));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(int|short|long)(?!\\w)", Token.NUMBER_DEC));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(double|float)(?!\\w)", Token.NUMBER_POINT));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(String|char)(?!\\w)", Token.TEXT));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(boolean)(?!\\w)", Token.BOOLEAN));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(byte)(?!\\w)", Token.BYTE));

        return tokens;
    }

    private static ArrayList<Token> compoundStatmentsTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "(?<!\\w)else\\{", Token.CONDITIONAL_STATEMENT));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)if\\(", Token.CONDITIONAL_STATEMENT));
        if (line.equals("else")) {
            tokens.add(Token.CONDITIONAL_STATEMENT);
        }

        tokens.addAll(findTokensRegex(line, "(?<!\\w)for\\(", Token.LOOP));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)while\\(", Token.LOOP));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)do\\{", Token.LOOP));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)switch\\(", Token.SWITCH));

        tokens.addAll(findTokensRegex(line, "(?<!\\w)try\\{", Token.TRY));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)catch\\(", Token.CATCH));

        return tokens;
    }

    private static ArrayList<Token> classVarTokenization(String line, ArrayList<Token> tokensSource) {
        ArrayList<Token> tokens = new ArrayList<>();

        line = line.replace("String", "");
        tokens.addAll(findTokensRegex(line, "[A-Z]\\w*\\s\\w+[;=,)]", Token.CLASS_VAR));

        return tokens;
    }

    private static ArrayList<Token> operatorsTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        // UWAGA: przy zmianie kolejnosci wywolan
        // Assign 1
        tokens.addAll(findTokensSequence(line, "<<=", Token.OP_ASSIGN));
        line = line.replace("<<=", " ");
        tokens.addAll(findTokensSequence(line, ">>=", Token.OP_ASSIGN));
        line = line.replace(">>=", " ");
        // Bitwise 1
        tokens.addAll(findTokensSequence(line, "<<", Token.OP_BITWISE));
        line = line.replace("<<", " ");
        tokens.addAll(findTokensSequence(line, ">>>", Token.OP_BITWISE));
        line = line.replace(">>>", " ");
        tokens.addAll(findTokensSequence(line, ">>", Token.OP_BITWISE));
        line = line.replace(">>", " ");

        // Generic
        tokens.addAll(findTokensRegex(line, "<\\w*>", Token.GENERIC));
        line = line.replaceAll("<\\w*>", "");

        // Relation
        tokens.addAll(findTokensSequence(line, "==", Token.OP_RELATION));
        line = line.replace("==", " ");
        tokens.addAll(findTokensSequence(line, "!=", Token.OP_RELATION));
        line = line.replace("!=", " ");
        tokens.addAll(findTokensSequence(line, ">=", Token.OP_RELATION));
        line = line.replace(">=", " ");
        tokens.addAll(findTokensSequence(line, "<=", Token.OP_RELATION));
        line = line.replace("<=", " ");
        tokens.addAll(findTokensSequence(line, ">", Token.OP_RELATION));
        line = line.replace(">", " ");
        tokens.addAll(findTokensSequence(line, "<", Token.OP_RELATION));
        line = line.replace("<", " ");

        // Logic
        tokens.addAll(findTokensSequence(line, "&&", Token.OP_LOGIC));
        line = line.replace("&&", " ");
        tokens.addAll(findTokensSequence(line, "||", Token.OP_LOGIC));
        line = line.replace("||", " ");
        tokens.addAll(findTokensSequence(line, "!", Token.OP_LOGIC));
        line = line.replace("!", " ");

        // Assign 2
        tokens.addAll(findTokensSequence(line, "+=", Token.OP_ASSIGN));
        line = line.replace("+=", " ");
        tokens.addAll(findTokensSequence(line, "-=", Token.OP_ASSIGN));
        line = line.replace("-=", " ");
        tokens.addAll(findTokensSequence(line, "*=", Token.OP_ASSIGN));
        line = line.replace("*=", " ");
        tokens.addAll(findTokensSequence(line, "/=", Token.OP_ASSIGN));
        line = line.replace("/=", " ");
        tokens.addAll(findTokensSequence(line, "%=", Token.OP_ASSIGN));
        line = line.replace("%=", " ");
        tokens.addAll(findTokensSequence(line, "&=", Token.OP_ASSIGN));
        line = line.replace("&=", " ");
        tokens.addAll(findTokensSequence(line, "^=", Token.OP_ASSIGN));
        line = line.replace("^=", " ");
        tokens.addAll(findTokensSequence(line, "|=", Token.OP_ASSIGN));
        line = line.replace("|=", " ");
        tokens.addAll(findTokensSequence(line, "=", Token.OP_ASSIGN));
        line = line.replace("=", " ");

        // Arithmetic
        tokens.addAll(findTokensSequence(line, "++", Token.OP_ARITHMETIC));
        line = line.replace("++", " ");
        tokens.addAll(findTokensSequence(line, "--", Token.OP_ARITHMETIC));
        line = line.replace("--", " ");
        tokens.addAll(findTokensSequence(line, "+", Token.OP_ARITHMETIC));
        line = line.replace("+", " ");
        tokens.addAll(findTokensSequence(line, "-", Token.OP_ARITHMETIC));
        line = line.replace("-", " ");
        tokens.addAll(findTokensSequence(line, "*", Token.OP_ARITHMETIC));
        line = line.replace("*", " ");
        tokens.addAll(findTokensSequence(line, "/", Token.OP_ARITHMETIC));
        line = line.replace("/", " ");
        tokens.addAll(findTokensSequence(line, "%", Token.OP_ARITHMETIC));
        line = line.replace("%", " ");

        // Bitwise 2
        tokens.addAll(findTokensSequence(line, "&", Token.OP_BITWISE));
        line = line.replace("&", " ");
        tokens.addAll(findTokensSequence(line, "|", Token.OP_BITWISE));
        line = line.replace("|", " ");
        tokens.addAll(findTokensSequence(line, "^", Token.OP_BITWISE));
        line = line.replace("^", " ");
        tokens.addAll(findTokensSequence(line, "~", Token.OP_BITWISE));
        line = line.replace("~", " ");

        return tokens;
    }

    private static ArrayList<Token> findTokensSequence(String str, String charSequence, Token token) {
        ArrayList<Token> tokens = new ArrayList<>();

        int count = StringUtils.countMatches(str, charSequence);
        for (int i = 0; i < count; i++) {
            tokens.add(token);
        }

        return tokens;
    }

    private static ArrayList<Token> findTokensRegex(String str, String regex, Token token) {
        ArrayList<Token> tokens = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            tokens.add(token);
        }

        return tokens;
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
            CodeFile normalizedCode = Normalization.codeNormalization(f);
            TokenFile tokenFile = convertTokenFile(normalizedCode);

            ArrayList<CodeLine> codeLines = normalizedCode.getCodeLines();
            ArrayList<TokenLine> tokenLines = tokenFile.getTokenLines();

            sb.append("\tFILE: " + f.getName() + "\n");
            for (int i = 0; i < codeLines.size(); i++) {
                String strCode = codeLines.get(i).toString();
                String strTokens = tokenLines.get(i).getTokens().toString();
                sb.append(strCode + "\t" + strTokens);
                sb.append(System.lineSeparator());
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
