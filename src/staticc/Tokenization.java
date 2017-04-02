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

public final class Tokenization {

    final static boolean SKIP_ARG_IN_COMPOUND_STATEMENTS = true;

    public static TokenFile tokenization(File file) {
        CodeFile codeFile = Normalization.codeNormalization(file);
        TokenFile tokenFile = convertTokenFile(codeFile);

        // TODO: usun
        System.out.println(codeFile.toString());
        System.out.println();
        System.out.println(tokenFile.toString());

        return tokenFile;
    }

    private static TokenFile convertTokenFile(CodeFile codeFile) {
        ArrayList<TokenLine> tokenLines = new ArrayList<>();

        for (CodeLine codeLine : codeFile.codeLines) {
            ArrayList<Token> tokens = convertTokenLine(codeLine.code);
            TokenLine tokenLine = new TokenLine(codeLine.lineNumber, tokens);
            tokenLines.add(tokenLine);
        }

        return new TokenFile(tokenLines);
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

        return tokens;
    }

    private static ArrayList<Token> functionsTokenization(String line, boolean newTokens) {
        ArrayList<Token> tokens = new ArrayList<>();

        if (newTokens == false) {
            boolean foundTokens = tokens.addAll(findTokensRegex(line, "(?<!\\w)\\w+\\(.*\\)\\{", Token.FUNCTION_DEF));

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
        tokens.addAll(findTokensRegex(line, "(?<!\\w)return;", Token.RETURN));

        return tokens;
    }

    private static ArrayList<Token> singleWordsTokenization(String line) {
        ArrayList<Token> tokens = new ArrayList<>();

        tokens.addAll(findTokensRegex(line, "(?<!\\w)(new)(?!\\w)", Token.NEW));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(class)(?!\\w)", Token.CLASS));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(extends)(?!\\w)", Token.EXTENDS));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(implements)(?!\\w)", Token.IMPLEMENTS));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(static)(?!\\w)", Token.STATIC));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(final)(?!\\w)", Token.FINAL));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(throws)(?!\\w)", Token.THROWS));
        tokens.addAll(findTokensRegex(line, "(?<!\\w)(void)(?!\\w)", Token.VOID));

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

        // Ochrona przed bledym wykryciem CLASS_VAR dla linii zawierajacych extends lub implements
        if (tokensSource.contains(Token.CLASS) == false) {
            line = line.replace("String", "");
            tokens.addAll(findTokensRegex(line, "[A-Z]\\w* \\w+", Token.CLASS_VAR));
        }

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

    public static ArrayList<TokenFile> tokenProject(Project project) {
        ArrayList<TokenFile> projectTokens = new ArrayList<>();

        for (File f : project.getFiles()) {
            TokenFile tokenFile = Tokenization.tokenization(f);
            projectTokens.add(tokenFile);
        }

        return projectTokens;
    }

    public static ArrayList<TokenFile> tokenProjects(Projects projects) {
        ArrayList<TokenFile> projectTokens = new ArrayList<>();

        for (Project p : projects.getProjects()) {
            projectTokens.addAll(Tokenization.tokenProject(p));
        }

        return projectTokens;
    }

}
