package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TableFragments;

public class TableFragmentsController implements Initializable {

    @FXML TableView<TableFragments> tableFragments;
    @FXML TableColumn<TableFragments, String> iFile1Fragment;
    @FXML TableColumn<TableFragments, String> iFile2Fragment;

    final ObservableList<TableFragments> data = FXCollections.observableArrayList(
        new TableFragments("MySuperClassController", 12, 32, "MainSourceController", 32, 54),
        new TableFragments("plik2", 78, 90, "plik3", 1, 13)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFile1Fragment.setCellValueFactory(new PropertyValueFactory<TableFragments, String>("rFile1Fragment"));
        iFile2Fragment.setCellValueFactory(new PropertyValueFactory<TableFragments, String>("rFile2Fragment"));

        tableFragments.setItems(data);
    }

}
