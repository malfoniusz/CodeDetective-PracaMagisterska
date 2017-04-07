package model.tokenization;

public class CodeLine {

    private int lineNumber;
    private String code;

    public CodeLine(int lineNumber, String code) {
        this.lineNumber = lineNumber;
        this.code = code;
    }

    @Override
    public String toString() {
        return new String(lineNumber + "\t" + code);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
