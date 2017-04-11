package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.CompareFiles;

public class MainController implements Initializable {

    @FXML private MenuController menuController;
    @FXML private CompareFilesController compareFilesController;
    @FXML private CompareFragmentsController compareFragmentsController;
    @FXML private CompareCodesController compareCodesController;

    private Compare compare;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        compare = new Compare();

        menuController.setMainController(this);
        menuController.setCompare(compare);
        menuController.updateIItemStart();

        compareFilesController.setCompareFragmentsController(compareFragmentsController);
        compareFilesController.setCompareCodesController(compareCodesController);

        compareFragmentsController.setCompareCodesController(compareCodesController);
    }

    public void runAlgorithm() {
        ArrayList<CompareFiles> compareFiles = compare.runCompare();

        if (compareFiles != null) {
            compareFilesController.setData(compareFiles);
        }
    }

    public MenuController getMenuController() {
        return this.menuController;
    }

    public CompareFilesController getCompareFilesController() {
        return this.compareFilesController;
    }

    public Compare getCompare() {
        return compare;
    }

    public void setCompare(Compare compare) {
        this.compare = compare;
    }

}
