package model.tokenization;

import java.util.ArrayList;

public class TokenFile {

    public ArrayList<TokenLine> tokenLines;

    public TokenFile(ArrayList<TokenLine> tokenLines) {
        this.tokenLines = tokenLines;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (TokenLine tokenLine : tokenLines) {
            sb.append(tokenLine.toString() + "\n");
        }

        String str = sb.toString();
        String noEndSpace = str.substring(0, str.length() - 1);
        return noEndSpace;
    }

}
