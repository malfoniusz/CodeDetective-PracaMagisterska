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

    private static boolean testPrint = true;

    public static TokenFile tokenization(File file) {
        CodeFile codeFile = prepareForTokenization(file);
        TokenFile tokenFile = convertTokenFile(codeFile);

        // TODO: usun
        if (file.getPath().contains("Algorytmy L Cw1\\Plecak.cpp") && testPrint == true) {
            testPrint = false;
            //System.out.println(codeFile.toString());
            System.out.println(tokenFile.toString());
        }

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

        if (line.startsWith("short") ||
                line.startsWith("int") ||
                line.startsWith("long") ||
                line.startsWith("float") ||
                line.startsWith("double")) {
            tokens.add(Token.NUMBER);
        }

        return tokens;
    }

    // Trims code, removes comments, etc.
    private static CodeFile prepareForTokenization(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<CodeLine> codeLines = new ArrayList<>();

            int lineNumber = 1;
            boolean commentStarted = false;
            String line = br.readLine();
            while (line != null) {
                // Nie przestawiać kolejności wywoływania instrukcji
                line = clearText(line);

                Pair<String, Boolean> pair = clearComments(line, commentStarted);
                line = pair.getKey();
                commentStarted = pair.getValue();

                line = line.trim();
                if ((line.startsWith("#") || line.startsWith("using"))) { // #include, #endif, using std::cout
                    line = "";
                }

                CodeLine codeLine = new CodeLine(lineNumber, line);
                codeLines.add(codeLine);
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

    private static String clearText(String line) {
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
