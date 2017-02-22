package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.Files;

public class FilesController implements Initializable {

    private TableFragmentsController tableFragmentsController;
    private CodesViewController codesViewController;

    @FXML private TableView<Files> files;
    @FXML private TableColumn<Files, String> iFile1;
    @FXML private TableColumn<Files, String> iProject;
    @FXML private TableColumn<Files, String> iFile2;
    @FXML private TableColumn<Files, Integer> iLines;
    @FXML private TableColumn<Files, String> iMatched;

    private final ObservableList<Files> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFile1.setCellValueFactory(new PropertyValueFactory<Files, String>("rFile1"));
        iProject.setCellValueFactory(new PropertyValueFactory<Files, String>("rProject"));
        iFile2.setCellValueFactory(new PropertyValueFactory<Files, String>("rFile2"));
        iLines.setCellValueFactory(new PropertyValueFactory<Files, Integer>("rLines"));
        iMatched.setCellValueFactory(new PropertyValueFactory<Files, String>("rMatched"));

        files.setItems(data);

        setRowFactory(files);
    }

    private void setRowFactory(TableView<Files> tableView) {
        tableView.setRowFactory(e -> {
            TableRow<Files> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (! tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    // Wyswietl fragmenty
                    Files files = tableRow.getItem();
                    tableFragmentsController.setData(files.getTableFragments());

                    // Wyczysc kod
                    codesViewController.clearCodes();
                }
            });
            return tableRow ;
        });
    }

    public void setData(ArrayList<Files> items) {
        data.setAll(items);
        sortTableByMatched();
    }

    private void sortTableByMatched() {
        files.getSortOrder().add(iMatched);
    }

    public ObservableList<Files> getData() {
        return data;
    }

    public void setData(ObservableList<Files> value) {
        data.setAll(value);
    }

    public void setTableFragmentsController(TableFragmentsController tableFragmentsController) {
        this.tableFragmentsController = tableFragmentsController;
    }

    public void setCodesViewController(CodesViewController codesViewController) {
        this.codesViewController = codesViewController;
    }

}
