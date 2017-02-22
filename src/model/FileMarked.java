package model;

import java.io.File;

public class FileMarked {

    private File file;
    private int fromLine;
    private int toLine;

    public FileMarked(File file, int fromLine, int toLine) {
        this.file = file;
        this.fromLine = fromLine;
        this.toLine = toLine;
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

}
