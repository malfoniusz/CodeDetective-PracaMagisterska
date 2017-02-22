package model;

import javafx.beans.property.SimpleStringProperty;

public class TableFragments {

    // Column File1Fragment
    private final SimpleStringProperty rFileFragment1;
    private FileMarked fileMarked1;

    // Column File2Fragment
    private final SimpleStringProperty rFileFragment2;
    private FileMarked fileMarked2;

    public TableFragments(FileMarked fileMarked1, FileMarked fileMarked2) {
        this.fileMarked1 = fileMarked1;
        this.rFileFragment1 = new SimpleStringProperty();
        updateRFileFragment1();

        this.fileMarked2 = fileMarked2;
        this.rFileFragment2 = new SimpleStringProperty();
        updateRFileFragment2();
    }

    public String getRFileFragment1() {
        return rFileFragment1.get();
    }

    private void updateRFileFragment1() {
        this.rFileFragment1.set(fileMarked1.getFile().getName() + " [" + fileMarked1.getFromLine() + "-" + fileMarked1.getToLine() + "]");
    }

    public FileMarked getFileMarked1() {
        return fileMarked1;
    }

    public void setFileMarked1(FileMarked fileMarked1) {
        this.fileMarked1 = fileMarked1;
        updateRFileFragment1();
    }


    public String getRFileFragment2() {
        return rFileFragment2.get();
    }

    private void updateRFileFragment2() {
        this.rFileFragment2.set(fileMarked2.getFile().getName() + " [" + fileMarked2.getFromLine() + "-" + fileMarked2.getToLine() + "]");
    }

    public FileMarked getFileMarked2() {
        return fileMarked2;
    }

    public void setFileMarked2(FileMarked fileMarked2) {
        this.fileMarked2 = fileMarked2;
        updateRFileFragment2();
    }

}
