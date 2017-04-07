package model.tokenization;

import java.io.File;
import java.util.ArrayList;

public class TokenFile {

    private File file;
    private ArrayList<TokenLine> tokenLines;

    public TokenFile(File file, ArrayList<TokenLine> tokenLines) {
        this.file = file;
        this.tokenLines = tokenLines;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tFILE: " + file.getName() + "\n");

        for (TokenLine tokenLine : tokenLines) {
            sb.append(tokenLine.toString() + "\n");
        }

        String str = sb.toString();
        String noEndSpace = str.substring(0, str.length() - 1);
        return noEndSpace;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<TokenLine> getTokenLines() {
        return tokenLines;
    }

    public void setTokenLines(ArrayList<TokenLine> tokenLines) {
        this.tokenLines = tokenLines;
    }

}
