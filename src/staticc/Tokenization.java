package staticc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.util.Pair;
import model.Project;
import model.Projects;
import model.token.TokenFile;
import model.token.TokenLine;

public final class Tokenization {

    private static boolean testPrint = true;

    public static TokenFile tokenization(File file) {
        String text = cleanUpFile(file);

        // TODO: usun
        if (file.getPath().contains("Algorytmy L Cw1\\Plecak.cpp") && testPrint == true) {
            testPrint = false;
            System.out.println(text);
        }

        // TODO: tokenizacja
        ArrayList<TokenLine> tokenLines = new ArrayList<>();
        TokenFile tokenFile = new TokenFile(tokenLines);
        return tokenFile;
    }

    private static String cleanUpFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();

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

                sb.append(lineNumber + "\t" + line + "\n");
                lineNumber++; // TODO: usun
                line = br.readLine();
            }

            return sb.toString();
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
