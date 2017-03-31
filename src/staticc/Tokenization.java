package staticc;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import model.Project;
import model.Projects;
import model.tokenization.CodeFile;
import model.tokenization.CodeLine;
import model.tokenization.Token;
import model.tokenization.TokenFile;
import model.tokenization.TokenLine;

public final class Tokenization {

    final static boolean SKIP_ARG_IN_STATEMENT_AND_LOOP = true;

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

        tokens.addAll(tokensInString(line, "else{", Token.STATEMENT));
        tokens.addAll(tokensInString(line, "if(", Token.STATEMENT));
        if (line.equals("else")) {
            tokens.add(Token.STATEMENT);
        }

        tokens.addAll(tokensInString(line, "for(", Token.LOOP));
        tokens.addAll(tokensInString(line, "while(", Token.LOOP));
        tokens.addAll(tokensInString(line, "do{", Token.LOOP));

        tokens.addAll(tokensInString(line, "switch(", Token.SWITCH));

        tokens.addAll(tokensInString(line, "try{", Token.TRY));
        tokens.addAll(tokensInString(line, "catch(", Token.CATCH));

        if (SKIP_ARG_IN_STATEMENT_AND_LOOP && tokens.isEmpty() == false) {
            return tokens;
        }

        tokens.addAll(typesTokenization(line));
        tokens.addAll(modifiersTokenization(line));

        tokens.addAll(tokensInString(line, "[]", Token.TABLE));

        tokens.addAll(tokensInString(line, "case", Token.CASE));
        tokens.addAll(tokensInString(line, "default:", Token.DEFAULT));

        tokens.addAll(tokensInString(line, "continue;", Token.CONTINUE));
        tokens.addAll(tokensInString(line, "break;", Token.BREAK));

        tokens.addAll(tokensInString(line, "return;", Token.RETURN));

        tokens.addAll(operatorsTokenization(line));

        return tokens;
    }

    private static ArrayList<Token> typesTokenization(String str) {
        ArrayList<Token> tokens = new ArrayList<>();

        if (str.matches(".*(?<!\\w)(int|short|long)(?!\\w).*")) {
            tokens.add(Token.NUMBER_DEC);
        }
        else if (str.matches(".*(?<!\\w)(double|float)(?!\\w).*")) {
            tokens.add(Token.NUMBER_POINT);
        }
        else if (str.matches(".*(?<!\\w)(String|char)(?!\\w).*")) {
            tokens.add(Token.TEXT);
        }
        else if (str.matches(".*(?<!\\w)(boolean)(?!\\w).*")) {
            tokens.add(Token.BOOLEAN);
        }
        else if (str.matches(".*(?<!\\w)(byte)(?!\\w).*")) {
            tokens.add(Token.BYTE);
        }
        else if (str.matches(".*(?<!\\w)(class)(?!\\w).*")) {
            tokens.add(Token.CLASS);
        }

        return tokens;
    }

    private static ArrayList<Token> modifiersTokenization(String str) {
        ArrayList<Token> tokens = new ArrayList<>();

        if (str.matches(".*(?<!\\w)(static)(?!\\w).*")) {
            tokens.add(Token.STATIC);
        }

        if (str.matches(".*(?<!\\w)(final)(?!\\w).*")) {
            tokens.add(Token.FINAL);
        }

        if (str.matches(".*(?<!\\w)(extends)(?!\\w).*")) {
            tokens.add(Token.EXTENDS);
        }

        if (str.matches(".*(?<!\\w)(implements)(?!\\w).*")) {
            tokens.add(Token.IMPLEMENTS);
        }

        return tokens;
    }

    private static ArrayList<Token> operatorsTokenization(String str) {
        ArrayList<Token> tokens = new ArrayList<>();

        // UWAGA: przy zmianie kolejnosci wywolan
        // Assign 1
        tokens.addAll(tokensInString(str, "<<=", Token.OP_ASSIGN));
        str = str.replace("<<=", " ");
        tokens.addAll(tokensInString(str, ">>=", Token.OP_ASSIGN));
        str = str.replace(">>=", " ");
        // Bitwise 1
        tokens.addAll(tokensInString(str, "<<", Token.OP_BITWISE));
        str = str.replace("<<", " ");
        tokens.addAll(tokensInString(str, ">>>", Token.OP_BITWISE));
        str = str.replace(">>>", " ");
        tokens.addAll(tokensInString(str, ">>", Token.OP_BITWISE));
        str = str.replace(">>", " ");

        // Relation
        tokens.addAll(tokensInString(str, "==", Token.OP_RELATION));
        str = str.replace("==", " ");
        tokens.addAll(tokensInString(str, "!=", Token.OP_RELATION));
        str = str.replace("!=", " ");
        tokens.addAll(tokensInString(str, ">=", Token.OP_RELATION));
        str = str.replace(">=", " ");
        tokens.addAll(tokensInString(str, "<=", Token.OP_RELATION));
        str = str.replace("<=", " ");
        tokens.addAll(tokensInString(str, ">", Token.OP_RELATION));
        str = str.replace(">", " ");
        tokens.addAll(tokensInString(str, "<", Token.OP_RELATION));
        str = str.replace("<", " ");

        // Logic
        tokens.addAll(tokensInString(str, "&&", Token.OP_LOGIC));
        str = str.replace("&&", " ");
        tokens.addAll(tokensInString(str, "||", Token.OP_LOGIC));
        str = str.replace("||", " ");
        tokens.addAll(tokensInString(str, "!", Token.OP_LOGIC));
        str = str.replace("!", " ");

        // Assign 2
        tokens.addAll(tokensInString(str, "+=", Token.OP_ASSIGN));
        str = str.replace("+=", " ");
        tokens.addAll(tokensInString(str, "-=", Token.OP_ASSIGN));
        str = str.replace("-=", " ");
        tokens.addAll(tokensInString(str, "*=", Token.OP_ASSIGN));
        str = str.replace("*=", " ");
        tokens.addAll(tokensInString(str, "/=", Token.OP_ASSIGN));
        str = str.replace("/=", " ");
        tokens.addAll(tokensInString(str, "%=", Token.OP_ASSIGN));
        str = str.replace("%=", " ");
        tokens.addAll(tokensInString(str, "&=", Token.OP_ASSIGN));
        str = str.replace("&=", " ");
        tokens.addAll(tokensInString(str, "^=", Token.OP_ASSIGN));
        str = str.replace("^=", " ");
        tokens.addAll(tokensInString(str, "|=", Token.OP_ASSIGN));
        str = str.replace("|=", " ");
        tokens.addAll(tokensInString(str, "=", Token.OP_ASSIGN));
        str = str.replace("=", " ");

        // Arithmetic
        tokens.addAll(tokensInString(str, "++", Token.OP_ARITHMETIC));
        str = str.replace("++", " ");
        tokens.addAll(tokensInString(str, "--", Token.OP_ARITHMETIC));
        str = str.replace("--", " ");
        tokens.addAll(tokensInString(str, "+", Token.OP_ARITHMETIC));
        str = str.replace("+", " ");
        tokens.addAll(tokensInString(str, "-", Token.OP_ARITHMETIC));
        str = str.replace("-", " ");
        tokens.addAll(tokensInString(str, "*", Token.OP_ARITHMETIC));
        str = str.replace("*", " ");
        tokens.addAll(tokensInString(str, "/", Token.OP_ARITHMETIC));
        str = str.replace("/", " ");
        tokens.addAll(tokensInString(str, "%", Token.OP_ARITHMETIC));
        str = str.replace("%", " ");

        // Bitwise 2
        tokens.addAll(tokensInString(str, "&", Token.OP_BITWISE));
        str = str.replace("&", " ");
        tokens.addAll(tokensInString(str, "|", Token.OP_BITWISE));
        str = str.replace("|", " ");
        tokens.addAll(tokensInString(str, "^", Token.OP_BITWISE));
        str = str.replace("^", " ");
        tokens.addAll(tokensInString(str, "~", Token.OP_BITWISE));
        str = str.replace("~", " ");

        return tokens;
    }

    private static ArrayList<Token> tokensInString(String str, String pattern, Token token) {
        ArrayList<Token> tokens = new ArrayList<>();

        int count = StringUtils.countMatches(str, pattern);
        for (int i = 0; i < count; i++) {
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
