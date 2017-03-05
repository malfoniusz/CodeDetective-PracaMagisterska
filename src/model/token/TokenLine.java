package model.token;

import java.util.ArrayList;

public class TokenLine {
    public int lineNumber;
    public ArrayList<Token> tokens;

    public TokenLine(int lineNumber, ArrayList<Token> tokens) {
        this.lineNumber = lineNumber;
        this.tokens = tokens;
    }
}
