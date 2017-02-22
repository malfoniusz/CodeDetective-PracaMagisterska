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
import model.TableFiles;

public class TableFilesController implements Initializable {

    private TableFragmentsController tableFragmentsController;
    private CodesViewController codesViewController;

    @FXML private TableView<TableFiles> tableFiles;
    @FXML private TableColumn<TableFiles, String> iFile1;
    @FXML private TableColumn<TableFiles, String> iProject;
    @FXML private TableColumn<TableFiles, String> iFile2;
    @FXML private TableColumn<TableFiles, Integer> iLines;
    @FXML private TableColumn<TableFiles, String> iMatched;

    private final ObservableList<TableFiles> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFile1.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rFile1"));
        iProject.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rProject"));
        iFile2.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rFile2"));
        iLines.setCellValueFactory(new PropertyValueFactory<TableFiles, Integer>("rLines"));
        iMatched.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rMatched"));

        tableFiles.setItems(data);

        setRowFactory(tableFiles);
    }

    private void setRowFactory(TableView<TableFiles> tableView) {
        tableView.setRowFactory(e -> {
            TableRow<TableFiles> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (! tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    // Wyswietl fragmenty
                    TableFiles tableFiles = tableRow.getItem();
                    tableFragmentsController.setData(tableFiles.getTableFragments());

                    // Wyczysc kod
                    codesViewController.clearCodes();
                }
            });
            return tableRow ;
        });
    }

    public void setData(ArrayList<TableFiles> items) {
        data.setAll(items);
        sortTableByMatched();
    }

    private void sortTableByMatched() {
        tableFiles.getSortOrder().add(iMatched);
    }

    public ObservableList<TableFiles> getData() {
        return data;
    }

    public void setData(ObservableList<TableFiles> value) {
        data.setAll(value);
    }

    public void setTableFragmentsController(TableFragmentsController tableFragmentsController) {
        this.tableFragmentsController = tableFragmentsController;
    }

    public void setCodesViewController(CodesViewController codesViewController) {
        this.codesViewController = codesViewController;
    }

}
