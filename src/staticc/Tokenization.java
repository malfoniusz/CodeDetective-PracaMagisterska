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

        line = removeArgumentsFromStatements(line);
        line = removeArgumentsFromLoops(line);

        String[] words = line.split(" ");
        for (String word : words) {
            tokens.addAll(typesTokenization(word));

            tokens.addAll(tokensInString(word, "[]", Token.TABLE));

            tokens.addAll(statementsTokenization(word));

            tokens.addAll(tokensInString(word, "for(", Token.LOOP));
            tokens.addAll(tokensInString(word, "while(", Token.LOOP));
            tokens.addAll(tokensInString(word, "do{", Token.LOOP));

            tokens.addAll(tokensInString(word, "switch(", Token.SWITCH));
            tokens.addAll(tokensInString(word, "case", Token.CASE));
            tokens.addAll(tokensInString(word, "default:", Token.DEFAULT));

            tokens.addAll(tokensInString(word, "continue;", Token.CONTINUE));
            tokens.addAll(tokensInString(word, "break;", Token.BREAK));

            tokens.addAll(tokensInString(word, "return;", Token.RETURN));

            tokens.addAll(operatorsTokenization(word));
        }

        return tokens;
    }

    private static String removeArgumentsFromStatements(String line) {
        String clearedLine = "";

        // TODO: uzupelnij
        // else if
        // else{
        // if(
        // else

        return clearedLine;
    }

    private static String removeArgumentsFromLoops(String line) {
        String clearedLine = "";

        // TODO: uzupelnij
        // for(
        // while(

        return clearedLine;
    }

    private static ArrayList<Token> statementsTokenization(String word) {
        ArrayList<Token> tokens = new ArrayList<>();

        // UWAGA: przy zmianie kolejnosci wywolan
        tokens.addAll(tokensInString(word, "else if(", Token.STATEMENT));
        word = word.replace("else if(", " ");
        tokens.addAll(tokensInString(word, "else{", Token.STATEMENT));
        word = word.replace("else{", " ");
        tokens.addAll(tokensInString(word, "if(", Token.STATEMENT));
        word = word.replace("if(", " ");
        tokens.addAll(tokensInString(word, "else", Token.STATEMENT));
        word = word.replace("else", " ");

        return tokens;
    }

    private static ArrayList<Token> typesTokenization(String word) {
        ArrayList<Token> tokens = new ArrayList<>();

        if (word.matches(".*(?<!\\w)(int|short|long)(?!\\w).*")) {
            tokens.add(Token.NUMBER_DEC);
        }
        else if (word.matches(".*(?<!\\w)(double|float)(?!\\w).*")) {
            tokens.add(Token.NUMBER_POINT);
        }
        else if (word.matches(".*(?<!\\w)(String|char)(?!\\w).*")) {
            tokens.add(Token.TEXT);
        }
        else if (word.matches(".*(?<!\\w)(boolean)(?!\\w).*")) {
            tokens.add(Token.BOOLEAN);
        }
        else if (word.matches(".*(?<!\\w)(byte)(?!\\w).*")) {
            tokens.add(Token.BYTE);
        }

        return tokens;
    }

    private static ArrayList<Token> operatorsTokenization(String word) {
        ArrayList<Token> tokens = new ArrayList<>();

        // UWAGA: przy zmianie kolejnosci wywolan
        // Assign 1
        tokens.addAll(tokensInString(word, "<<=", Token.OP_ASSIGN));
        word = word.replace("<<=", " ");
        tokens.addAll(tokensInString(word, ">>=", Token.OP_ASSIGN));
        word = word.replace(">>=", " ");
        // Bitwise 1
        tokens.addAll(tokensInString(word, "<<", Token.OP_BITWISE));
        word = word.replace("<<", " ");
        tokens.addAll(tokensInString(word, ">>>", Token.OP_BITWISE));
        word = word.replace(">>>", " ");
        tokens.addAll(tokensInString(word, ">>", Token.OP_BITWISE));
        word = word.replace(">>", " ");

        // Relation
        tokens.addAll(tokensInString(word, "==", Token.OP_RELATION));
        word = word.replace("==", " ");
        tokens.addAll(tokensInString(word, "!=", Token.OP_RELATION));
        word = word.replace("!=", " ");
        tokens.addAll(tokensInString(word, ">=", Token.OP_RELATION));
        word = word.replace(">=", " ");
        tokens.addAll(tokensInString(word, "<=", Token.OP_RELATION));
        word = word.replace("<=", " ");
        tokens.addAll(tokensInString(word, ">", Token.OP_RELATION));
        word = word.replace(">", " ");
        tokens.addAll(tokensInString(word, "<", Token.OP_RELATION));
        word = word.replace("<", " ");

        // Logic
        tokens.addAll(tokensInString(word, "&&", Token.OP_LOGIC));
        word = word.replace("&&", " ");
        tokens.addAll(tokensInString(word, "||", Token.OP_LOGIC));
        word = word.replace("||", " ");
        tokens.addAll(tokensInString(word, "!", Token.OP_LOGIC));
        word = word.replace("!", " ");

        // Assign 2
        tokens.addAll(tokensInString(word, "+=", Token.OP_ASSIGN));
        word = word.replace("+=", " ");
        tokens.addAll(tokensInString(word, "-=", Token.OP_ASSIGN));
        word = word.replace("-=", " ");
        tokens.addAll(tokensInString(word, "*=", Token.OP_ASSIGN));
        word = word.replace("*=", " ");
        tokens.addAll(tokensInString(word, "/=", Token.OP_ASSIGN));
        word = word.replace("/=", " ");
        tokens.addAll(tokensInString(word, "%=", Token.OP_ASSIGN));
        word = word.replace("%=", " ");
        tokens.addAll(tokensInString(word, "&=", Token.OP_ASSIGN));
        word = word.replace("&=", " ");
        tokens.addAll(tokensInString(word, "^=", Token.OP_ASSIGN));
        word = word.replace("^=", " ");
        tokens.addAll(tokensInString(word, "|=", Token.OP_ASSIGN));
        word = word.replace("|=", " ");
        tokens.addAll(tokensInString(word, "=", Token.OP_ASSIGN));
        word = word.replace("=", " ");

        // Arithmetic
        tokens.addAll(tokensInString(word, "++", Token.OP_ARITHMETIC));
        word = word.replace("++", " ");
        tokens.addAll(tokensInString(word, "--", Token.OP_ARITHMETIC));
        word = word.replace("--", " ");
        tokens.addAll(tokensInString(word, "+", Token.OP_ARITHMETIC));
        word = word.replace("+", " ");
        tokens.addAll(tokensInString(word, "-", Token.OP_ARITHMETIC));
        word = word.replace("-", " ");
        tokens.addAll(tokensInString(word, "*", Token.OP_ARITHMETIC));
        word = word.replace("*", " ");
        tokens.addAll(tokensInString(word, "/", Token.OP_ARITHMETIC));
        word = word.replace("/", " ");
        tokens.addAll(tokensInString(word, "%", Token.OP_ARITHMETIC));
        word = word.replace("%", " ");

        // Bitwise 2
        tokens.addAll(tokensInString(word, "&", Token.OP_BITWISE));
        word = word.replace("&", " ");
        tokens.addAll(tokensInString(word, "|", Token.OP_BITWISE));
        word = word.replace("|", " ");
        tokens.addAll(tokensInString(word, "^", Token.OP_BITWISE));
        word = word.replace("^", " ");
        tokens.addAll(tokensInString(word, "~", Token.OP_BITWISE));
        word = word.replace("~", " ");

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
