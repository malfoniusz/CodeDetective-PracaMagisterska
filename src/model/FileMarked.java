package model;

import java.io.File;

public class FileMarked {

    private File file;
    private int fromLine;   // Numer lini kodu zrodlowego
    private int toLine;
    private int length;     // Dlugosc stokenizowanego fragmentu kodu. Raczej bedzie mniejsza od fromLine - toLine + 1

    public FileMarked(File file, int fromLine, int toLine, int length) {
        this.file = file;
        this.fromLine = fromLine;
        this.toLine = toLine;
        this.length = length;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getFromLine() {
        return fromLine;
    }

    public void setFromLine(int fromLine) {
        this.fromLine = fromLine;
    }

    public int getToLine() {
        return toLine;
    }

    public void setToLine(int toLine) {
        this.toLine = toLine;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
