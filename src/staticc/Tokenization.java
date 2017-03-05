package staticc;

import java.io.File;
import java.util.ArrayList;

import model.Project;
import model.Projects;
import model.token.TokenFile;
import model.token.TokenLine;

public final class Tokenization {

    public static TokenFile tokenization(File file) {
        File prepareFile = prepareFileForTokenization(file);

        // TODO: tokenizacja przygotowanego pliku
        ArrayList<TokenLine> tokenLines = new ArrayList<>();
        TokenFile tokenFile = new TokenFile(tokenLines);
        return tokenFile;
    }

    private static File prepareFileForTokenization(File file) {

        return null;
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
