package model.token;

public class CodeLine {

    public int lineNumber;
    public String code;

    public CodeLine(int lineNumber, String code) {
        this.lineNumber = lineNumber;
        this.code = code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lineNumber + "\t" + code);
        return sb.toString();
    }

}
