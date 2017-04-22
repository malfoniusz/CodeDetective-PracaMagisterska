package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.CompareFiles;
import staticc.Compare;

public class MainController implements Initializable {

    @FXML private MenuController menuController;
    @FXML private CompareFilesController compareFilesController;
    @FXML private CompareFragmentsController compareFragmentsController;
    @FXML private CompareCodesController compareCodesController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuController.setMainController(this);
        menuController.updateIItemStart();

        compareFilesController.setCompareFragmentsController(compareFragmentsController);
        compareFilesController.setCompareCodesController(compareCodesController);

        compareFragmentsController.setCompareCodesController(compareCodesController);
    }

    public void runAlgorithm() {
        ArrayList<CompareFiles> compareFiles = Compare.runCompare();

        if (compareFiles != null) {
            compareFilesController.setData(compareFiles);
        }
    }

    public MenuController getMenuController() {
        return this.menuController;
    }

    public CompareFragmentsController getCompareFragmentsController() {
        return this.compareFragmentsController;
    }

    public CompareCodesController getCompareCodesController() {
        return this.compareCodesController;
    }

    public CompareFilesController getCompareFilesController() {
        return this.compareFilesController;
    }

}
