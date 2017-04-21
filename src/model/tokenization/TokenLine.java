package model.tokenization;

import java.util.ArrayList;

public class TokenLine {

    private int lineNumber;
    private int tokenNumber;
    private ArrayList<Token> tokens;

    public TokenLine(int lineNumber, int tokenNumber, ArrayList<Token> tokens) {
        this.lineNumber = lineNumber;
        this.tokenNumber = tokenNumber;
        this.tokens = tokens;
    }

    public String createTokenStringStream() {
        StringBuilder sb = new StringBuilder();

        for (Token token : tokens) {
            sb.append(token.toString() + " ");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(lineNumber + "\t");
        sb.append("T:" + tokenNumber + "\t");

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

    public int getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(int tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

}
