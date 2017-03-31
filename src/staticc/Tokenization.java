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

        tokens.addAll(tokensInStringSeq(line, "else{", Token.STATEMENT));
        tokens.addAll(tokensInStringSeq(line, "if(", Token.STATEMENT));
        if (line.equals("else")) {
            tokens.add(Token.STATEMENT);
        }

        tokens.addAll(tokensInStringSeq(line, "for(", Token.LOOP));
        tokens.addAll(tokensInStringSeq(line, "while(", Token.LOOP));
        tokens.addAll(tokensInStringSeq(line, "do{", Token.LOOP));

        tokens.addAll(tokensInStringSeq(line, "switch(", Token.SWITCH));

        tokens.addAll(tokensInStringSeq(line, "try{", Token.TRY));
        tokens.addAll(tokensInStringSeq(line, "catch(", Token.CATCH));

        if (SKIP_ARG_IN_STATEMENT_AND_LOOP && tokens.isEmpty() == false) {
            return tokens;
        }

        if (tokens.isEmpty()) {
            tokens.addAll(tokensInStringRegex(line, "\\w\\(.*\\)\\{", Token.FUNCTION_DEF));
        }

        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(static)(?!\\w)", Token.STATIC));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(final)(?!\\w)", Token.FINAL));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(extends)(?!\\w)", Token.EXTENDS));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(implements)(?!\\w)", Token.IMPLEMENTS));

        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(class)(?!\\w)", Token.CLASS));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(void)(?!\\w)", Token.VOID));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(int|short|long)(?!\\w)", Token.NUMBER_DEC));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(double|float)(?!\\w)", Token.NUMBER_POINT));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(String|char)(?!\\w)", Token.TEXT));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(boolean)(?!\\w)", Token.BOOLEAN));
        tokens.addAll(tokensInStringRegex(line, "(?<!\\w)(byte)(?!\\w)", Token.BYTE));

        tokens.addAll(tokensInStringSeq(line, "[]", Token.TABLE));

        tokens.addAll(tokensInStringSeq(line, "case", Token.CASE));
        tokens.addAll(tokensInStringSeq(line, "default:", Token.DEFAULT));

        tokens.addAll(tokensInStringSeq(line, "continue;", Token.CONTINUE));
        tokens.addAll(tokensInStringSeq(line, "break;", Token.BREAK));

        tokens.addAll(tokensInStringSeq(line, "return;", Token.RETURN));

        tokens.addAll(operatorsTokenization(line));

        return tokens;
    }

    private static ArrayList<Token> operatorsTokenization(String str) {
        ArrayList<Token> tokens = new ArrayList<>();

        // UWAGA: przy zmianie kolejnosci wywolan
        // Assign 1
        tokens.addAll(tokensInStringSeq(str, "<<=", Token.OP_ASSIGN));
        str = str.replace("<<=", " ");
        tokens.addAll(tokensInStringSeq(str, ">>=", Token.OP_ASSIGN));
        str = str.replace(">>=", " ");
        // Bitwise 1
        tokens.addAll(tokensInStringSeq(str, "<<", Token.OP_BITWISE));
        str = str.replace("<<", " ");
        tokens.addAll(tokensInStringSeq(str, ">>>", Token.OP_BITWISE));
        str = str.replace(">>>", " ");
        tokens.addAll(tokensInStringSeq(str, ">>", Token.OP_BITWISE));
        str = str.replace(">>", " ");

        // Relation
        tokens.addAll(tokensInStringSeq(str, "==", Token.OP_RELATION));
        str = str.replace("==", " ");
        tokens.addAll(tokensInStringSeq(str, "!=", Token.OP_RELATION));
        str = str.replace("!=", " ");
        tokens.addAll(tokensInStringSeq(str, ">=", Token.OP_RELATION));
        str = str.replace(">=", " ");
        tokens.addAll(tokensInStringSeq(str, "<=", Token.OP_RELATION));
        str = str.replace("<=", " ");
        tokens.addAll(tokensInStringSeq(str, ">", Token.OP_RELATION));
        str = str.replace(">", " ");
        tokens.addAll(tokensInStringSeq(str, "<", Token.OP_RELATION));
        str = str.replace("<", " ");

        // Logic
        tokens.addAll(tokensInStringSeq(str, "&&", Token.OP_LOGIC));
        str = str.replace("&&", " ");
        tokens.addAll(tokensInStringSeq(str, "||", Token.OP_LOGIC));
        str = str.replace("||", " ");
        tokens.addAll(tokensInStringSeq(str, "!", Token.OP_LOGIC));
        str = str.replace("!", " ");

        // Assign 2
        tokens.addAll(tokensInStringSeq(str, "+=", Token.OP_ASSIGN));
        str = str.replace("+=", " ");
        tokens.addAll(tokensInStringSeq(str, "-=", Token.OP_ASSIGN));
        str = str.replace("-=", " ");
        tokens.addAll(tokensInStringSeq(str, "*=", Token.OP_ASSIGN));
        str = str.replace("*=", " ");
        tokens.addAll(tokensInStringSeq(str, "/=", Token.OP_ASSIGN));
        str = str.replace("/=", " ");
        tokens.addAll(tokensInStringSeq(str, "%=", Token.OP_ASSIGN));
        str = str.replace("%=", " ");
        tokens.addAll(tokensInStringSeq(str, "&=", Token.OP_ASSIGN));
        str = str.replace("&=", " ");
        tokens.addAll(tokensInStringSeq(str, "^=", Token.OP_ASSIGN));
        str = str.replace("^=", " ");
        tokens.addAll(tokensInStringSeq(str, "|=", Token.OP_ASSIGN));
        str = str.replace("|=", " ");
        tokens.addAll(tokensInStringSeq(str, "=", Token.OP_ASSIGN));
        str = str.replace("=", " ");

        // Arithmetic
        tokens.addAll(tokensInStringSeq(str, "++", Token.OP_ARITHMETIC));
        str = str.replace("++", " ");
        tokens.addAll(tokensInStringSeq(str, "--", Token.OP_ARITHMETIC));
        str = str.replace("--", " ");
        tokens.addAll(tokensInStringSeq(str, "+", Token.OP_ARITHMETIC));
        str = str.replace("+", " ");
        tokens.addAll(tokensInStringSeq(str, "-", Token.OP_ARITHMETIC));
        str = str.replace("-", " ");
        tokens.addAll(tokensInStringSeq(str, "*", Token.OP_ARITHMETIC));
        str = str.replace("*", " ");
        tokens.addAll(tokensInStringSeq(str, "/", Token.OP_ARITHMETIC));
        str = str.replace("/", " ");
        tokens.addAll(tokensInStringSeq(str, "%", Token.OP_ARITHMETIC));
        str = str.replace("%", " ");

        // Bitwise 2
        tokens.addAll(tokensInStringSeq(str, "&", Token.OP_BITWISE));
        str = str.replace("&", " ");
        tokens.addAll(tokensInStringSeq(str, "|", Token.OP_BITWISE));
        str = str.replace("|", " ");
        tokens.addAll(tokensInStringSeq(str, "^", Token.OP_BITWISE));
        str = str.replace("^", " ");
        tokens.addAll(tokensInStringSeq(str, "~", Token.OP_BITWISE));
        str = str.replace("~", " ");

        return tokens;
    }

    private static ArrayList<Token> tokensInStringSeq(String str, String charSequence, Token token) {
        ArrayList<Token> tokens = new ArrayList<>();

        int count = StringUtils.countMatches(str, charSequence);
        for (int i = 0; i < count; i++) {
            tokens.add(token);
        }

        return tokens;
    }

    private static ArrayList<Token> tokensInStringRegex(String str, String regex, Token token) {
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
