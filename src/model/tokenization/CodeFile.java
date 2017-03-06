package model.tokenization;

import java.util.ArrayList;

public class CodeFile {

    public ArrayList<CodeLine> codeLines;

    public CodeFile(ArrayList<CodeLine> codeLines) {
        this.codeLines = codeLines;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (CodeLine codeLine : codeLines) {
            sb.append(codeLine.toString() + "\n");
        }

        String str = sb.toString();
        String noEndLineSeparator = str.substring(0, str.length() - 1);
        return noEndLineSeparator;
    }

}
