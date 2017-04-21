package model.tokenization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TokenFile {

    private File file;
    private int totalLines;
    private int totalTokenLines;
    private ArrayList<TokenLine> tokenLines;

    public TokenFile(File file, ArrayList<TokenLine> tokenLines) {
        this.file = file;
        this.totalLines = totalLines(this.file);
        this.totalTokenLines = tokenLines.size();
        this.tokenLines = tokenLines;
    }

    public String createTokenLineStrings() {
        StringBuilder sb = new StringBuilder();

        for (TokenLine tokenLine : tokenLines) {
            sb.append(tokenLine.createTokenLineStrings() + " ");
        }

        return sb.toString();
    }

    public int codeLineDistance(int tokenLineIndex, int normDistance) {
        TokenLine tokenLine1 = tokenLines.get(tokenLineIndex);
        TokenLine tokenLine2 = tokenLines.get(tokenLineIndex + normDistance - 1);

        return tokenLine2.getLineNumber() - tokenLine1.getLineNumber();
    }

    private int totalLines(File file) {
        int totalLines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                totalLines++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return totalLines;
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

    public int getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }

    public int getTotalTokenLines() {
        return totalTokenLines;
    }

    public void setTotalTokenLines(int totalTokenLines) {
        this.totalTokenLines = totalTokenLines;
    }

    public ArrayList<TokenLine> getTokenLines() {
        return tokenLines;
    }

    public void setTokenLines(ArrayList<TokenLine> tokenLines) {
        this.tokenLines = tokenLines;
    }

}
