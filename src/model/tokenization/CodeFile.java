package model.tokenization;

import java.io.File;
import java.util.ArrayList;

public class CodeFile {

    public File file;
    public ArrayList<CodeLine> codeLines;

    public CodeFile(File file, ArrayList<CodeLine> codeLines) {
        this.file = file;
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
