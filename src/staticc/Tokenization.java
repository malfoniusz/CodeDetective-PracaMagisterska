package staticc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        CodeFile codeFile = codeNormalization(file);
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
        String[] words = line.split(" ");

        for (String str : words) {
            // Types

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

            // Table
            tokens.addAll(tokensInString(str, "[]", Token.TABLE));

            // Operators - UWAGA: przy zmianie kolejnosci wywolan
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
        }

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

    // Trim, remove comments, etc.
    private static CodeFile codeNormalization(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<CodeLine> codeLines = new ArrayList<>();

            int lineNumber = 1;
            String line = br.readLine();

            String combinedLine = "";
            int combinedLineNumber = 0;

            while (line != null) {
                line = clearLine(line);

                // Koniec linii kodu
                if (line.indexOf(";") != -1 || line.indexOf("{") != -1 ||
                        (line.startsWith("if") || line.startsWith("else") || line.startsWith("while") || line.startsWith("for")) && line.endsWith(")")) {
                    if (combinedLine.isEmpty() && line.isEmpty() == false) {
                        CodeLine codeLine = new CodeLine(lineNumber, line);
                        codeLines.add(codeLine);
                    }
                    else {
                        line = combinedLine + " " + line;

                        CodeLine codeLine = new CodeLine(combinedLineNumber, line);
                        codeLines.add(codeLine);

                        combinedLine = "";
                        combinedLineNumber = 0;
                    }
                }
                else if (line.isEmpty() == false) {
                    combinedLine += " " + line;

                    String combinedLineCleared = clearMultiComment(combinedLine);
                    if (combinedLine.equals(combinedLineCleared) == false) {
                        combinedLineNumber = 0;
                        combinedLine = combinedLineCleared;
                    }

                    if (combinedLineNumber == 0) {
                        combinedLineNumber = lineNumber;
                    }

                    combinedLine = combinedLine.trim();
                }

                line = br.readLine();
                lineNumber++;
            }

            CodeFile codeFile = new CodeFile(codeLines);
            return codeFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String clearLine(String line) {
        // UWAGA przy przestawianiu kolejności wywoływanych instrukcji
        line = clearStrings(line);
        line = clearComment(line);
        line = line.replace("}", "");
        line = clearAccess(line);

        line = line.trim();
        if (line.startsWith("import")) {
            line = "";
        }
        if (line.startsWith("@")) {
            line = "";
        }

        // Usuwa niepotrzebne spacje
        line = line.replaceAll("(?<!\\w)(\\s)|(\\s)(?!\\w)", "");

        return line;
    }

    private static String clearAccess(String line) {
        line = line.replace("public ", "");
        line = line.replace("protected ", "");
        line = line.replace("private ", "");

        return line;
    }

    private static String clearStrings(String line) {
        if (line.contains("\"")) {  // "tes\"st" = ""
            line = line.replace("\\\"", "");
            line = line.replaceAll("\".*?\"", "\"\"");
        }

        if (line.contains("\'")) {  // '\'' = ''
            line = line.replace("\\\'", "");
            line = line.replaceAll("\'.*?\'", "\'\'");
        }

        return line;
    }

    private static String clearComment(String line) {
        if (line.contains("//")) {
            line = line.replaceAll("\\/\\/.*", "");
        }

        line = clearMultiComment(line);

        return line;
    }

    private static String clearMultiComment(String line) {
        if (line.indexOf("/*") != -1 && line.indexOf("*/") != -1) {
            line = line.replaceAll("\\/\\*.*\\*\\/", "");
        }

        return line;
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
