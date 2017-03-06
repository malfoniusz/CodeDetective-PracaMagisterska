package model.tokenization;

public class CodeLine {

    public int lineNumber;
    public String code;

    public CodeLine(int lineNumber, String code) {
        this.lineNumber = lineNumber;
        this.code = code;
    }

    @Override
    public String toString() {
        return new String(lineNumber + "\t" + code);
    }

}
