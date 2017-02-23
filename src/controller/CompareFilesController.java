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
import model.CompareFiles;

public class CompareFilesController implements Initializable {

    private CompareFragmentsController compareFragmentsController;
    private CompareCodesController compareCodesController;

    @FXML private TableView<CompareFiles> compareFiles;
    @FXML private TableColumn<CompareFiles, String> iFile1;
    @FXML private TableColumn<CompareFiles, String> iProject;
    @FXML private TableColumn<CompareFiles, String> iFile2;
    @FXML private TableColumn<CompareFiles, Integer> iLines;
    @FXML private TableColumn<CompareFiles, String> iMatched;

    private final ObservableList<CompareFiles> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFile1.setCellValueFactory(new PropertyValueFactory<CompareFiles, String>("rFile1"));
        iProject.setCellValueFactory(new PropertyValueFactory<CompareFiles, String>("rProject"));
        iFile2.setCellValueFactory(new PropertyValueFactory<CompareFiles, String>("rFile2"));
        iLines.setCellValueFactory(new PropertyValueFactory<CompareFiles, Integer>("rLines"));
        iMatched.setCellValueFactory(new PropertyValueFactory<CompareFiles, String>("rMatched"));

        compareFiles.setItems(data);

        setRowFactory(compareFiles);
    }

    private void setRowFactory(TableView<CompareFiles> tableView) {
        tableView.setRowFactory(e -> {
            TableRow<CompareFiles> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (! tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    CompareFiles compareFiles = tableRow.getItem();
                    // Ustaw naglowek tabel
                    compareFragmentsController.setColumnName(1, compareFiles.getFile1().getPath());
                    compareFragmentsController.setColumnName(2, compareFiles.getFile2().getPath());

                    // Wyswietl fragmenty
                    compareFragmentsController.setData(compareFiles.getCompareFragments());

                    // Wyczysc kod
                    compareCodesController.clearCodes();
                }
            });
            return tableRow ;
        });
    }

    public void setData(ArrayList<CompareFiles> items) {
        data.setAll(items);
        sortTableByMatched();
    }

    private void sortTableByMatched() {
        compareFiles.getSortOrder().add(iMatched);
    }

    public ObservableList<CompareFiles> getData() {
        return data;
    }

    public void setData(ObservableList<CompareFiles> value) {
        data.setAll(value);
    }

    public void setCompareFragmentsController(CompareFragmentsController compareFragmentsController) {
        this.compareFragmentsController = compareFragmentsController;
    }

    public void setCompareCodesController(CompareCodesController compareCodesController) {
        this.compareCodesController = compareCodesController;
    }

}
