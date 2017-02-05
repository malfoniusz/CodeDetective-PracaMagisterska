package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainController implements Initializable {

    @FXML private TableFilesController tableFilesController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public TableFilesController getTableFilesController() {
        return tableFilesController;
    }

}
