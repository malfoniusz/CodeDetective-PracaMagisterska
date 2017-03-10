package staticc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.util.Pair;
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
        //System.out.println(tokenFile.toString());

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

        // TODO: pobieraj słowo po słowie i podejmuj kolejne decyzje na podstawie aktualnego słowa

        return tokens;
    }

    // Trim, remove comments, etc.
    @SuppressWarnings ("unused")
    private static CodeFile codeNormalization(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<CodeLine> codeLines = new ArrayList<>();

            int lineNumber = 1;
            boolean commentStarted = false;
            String line = br.readLine();

            String combinedLine = "";
            int combinedLineNumber = 0;

            while (line != null) {
                // Nie przestawiać kolejności wywoływania instrukcji
                line = clearStrings(line);

                Pair<String, Boolean> pair = clearComments(line, commentStarted);
                line = pair.getKey();
                commentStarted = pair.getValue();

                line = line.replace("}", "");   // {

                line = clearAccess(line);       // public, protected, private

                line = line.trim();
                if ((line.startsWith("import") ||   // import javax.swing.JButton;
                        line.startsWith("@") )) {   // @Override. itp.
                    line = "";
                }

                if (line.isEmpty() == false) {
                    int length = line.length();
                    if (line.indexOf(';') == -1 && line.indexOf('{') == -1) {
                        // Kod nie kończy się w danej linii np. int [ENTER]
                        combinedLine += line + " ";
                        if (combinedLineNumber == 0) {
                            combinedLineNumber = lineNumber;
                        }
                    }
                    else if (line.indexOf(';') + 1 < length && line.indexOf('{') + 1 < length) {
                        // Kod zawiera kilka linii np. int a; int b;
                        // Pierwsza linia jest zapisywana, a reszta ponownie przetwarzana
                        String splitLine;
                        if (line.indexOf(';') != -1) {
                            splitLine = line.substring(0, line.indexOf(';') + 1);
                            line = line.substring(line.indexOf(';') + 1);
                        }
                        else {
                            splitLine = line.substring(0, line.indexOf('{') + 1);
                            line = line.substring(line.indexOf('{') + 1);
                        }

                        CodeLine codeLine = new CodeLine(lineNumber, splitLine);
                        codeLines.add(codeLine);

                        continue;
                    }
                    else if (combinedLine.isEmpty()) {
                        // Kod kończy się w danej linii np. int a;
                        CodeLine codeLine = new CodeLine(lineNumber, line);
                        codeLines.add(codeLine);
                    }
                    else {
                        // Kod rozpoczęty we wcześniejszej linii kończy się np. int [ENTER] a;
                        CodeLine codeLine = new CodeLine(combinedLineNumber, combinedLine + line);
                        codeLines.add(codeLine);

                        combinedLine = "";
                        combinedLineNumber = 0;
                    }
                }

                lineNumber++;
                line = br.readLine();
            }

            CodeFile codeFile = new CodeFile(codeLines);
            return codeFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    private static Pair<String, Boolean> clearComments(String line, boolean commentStarted) {
        while (true) {
            int commentStart = line.indexOf("/*");
            int commentEnd = line.indexOf("*/");

            if (line.contains("//")) {
                line = line.replaceAll("\\/\\/.*", "");
                continue;
            }

            if (commentStart != -1 && commentEnd == -1) {       // Komentarz sie zaczyna
                line = line.replaceAll("\\/\\*.*", "");
                commentStarted = true;
                continue;
            }
            else if (commentStart == -1 && commentEnd != -1) {  // Komentarz sie konczy
                line = line.replaceAll(".*\\*\\/", "");
                commentStarted = false;
                continue;
            }
            else if (commentStart < commentEnd) {               // Komentarz sie zaczyna i konczy
                line = line.replaceAll("\\/\\*.*\\*\\/", "");
                continue;
            }
            else if (commentStart > commentEnd) {               // komentarz konczy sie i zaczyna
                line = line.replaceAll("\\*\\/.*\\/\\*", "");
                continue;
            }
            else if (commentStarted == true) {
                line = "";
                break;
            }
            else {
                break;
            }
        }

        return new Pair<>(line, commentStarted);
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
