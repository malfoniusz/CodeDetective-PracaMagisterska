package model.tokenization;

import java.io.File;
import java.util.ArrayList;

public class TokenProject {

    private File directory;
    private String name;
    private ArrayList<TokenFile> tokenFiles;

    public TokenProject(File directory, ArrayList<TokenFile> tokenFiles) {
        this.directory = directory;
        this.name = directory.getName();
        this.tokenFiles = tokenFiles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (TokenFile tokenFile : tokenFiles) {
            sb.append(tokenFile.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TokenFile> getTokenFiles() {
        return tokenFiles;
    }

    public void setTokenFiles(ArrayList<TokenFile> tokenFiles) {
        this.tokenFiles = tokenFiles;
    }

}
