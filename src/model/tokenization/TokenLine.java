package model.tokenization;

import java.util.ArrayList;

public class TokenLine {

    public int lineNumber;
    public ArrayList<Token> tokens;

    public TokenLine(int lineNumber, ArrayList<Token> tokens) {
        this.lineNumber = lineNumber;
        this.tokens = tokens;
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

}
