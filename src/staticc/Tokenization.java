package staticc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
            if (str.equals("int") || str.equals("short") || str.equals("long")) {
                tokens.add(Token.NUMBER_DEC);
            }
            else if (str.equals("double") || str.equals("float")) {
                tokens.add(Token.NUMBER_POINT);
            }
            else if (str.equals("boolean")) {
                tokens.add(Token.BOOLEAN);
            }
            else if (str.contains("++")) {
                tokens.add(Token.INCREMENT);
            }
            else if (str.contains("--")) {
                tokens.add(Token.DECREMENT);
            }
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
                line = clearLine(line).trim();

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
