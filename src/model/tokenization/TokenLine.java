package model.tokenization;

import java.util.ArrayList;

public class TokenLine {

    private int lineNumber;
    private ArrayList<Token> tokens;

    public TokenLine(int lineNumber, ArrayList<Token> tokens) {
        this.lineNumber = lineNumber;
        this.tokens = tokens;
    }

    public String createTokenLineStrings() {
        StringBuilder sb = new StringBuilder();

        for (Token token : tokens) {
            sb.append(token.toString() + "_");  // Uzycie innego znaku moze zepsuc dzialanie algorytmu
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(lineNumber + "\t");

        for (Token token : tokens) {
            sb.append(token + " ");
        }

        String str = sb.toString();
        String noEndSpace = str.substring(0, str.length() - 1);
        return noEndSpace;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

}
