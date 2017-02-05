package main;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TableFiles;

public class MainController implements Initializable {

    // Define TableFiles
    @FXML TableView<TableFiles> iTableFiles;
    @FXML TableColumn<TableFiles, String> iFile1;
    @FXML TableColumn<TableFiles, String> iFile2;
    @FXML TableColumn<TableFiles, Integer> iLines;
    @FXML TableColumn<TableFiles, String> iMatched;

    // create table data
    final ObservableList<TableFiles> data = FXCollections.observableArrayList(
        new TableFiles("plik1 (365)", "plik3 (566)", 222267, "58%"),
        new TableFiles("plik2 (205)", "plik3 (566)", 144187, "95%")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFile1.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rFile1"));
        iFile2.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rFile2"));
        iLines.setCellValueFactory(new PropertyValueFactory<TableFiles, Integer>("rLines"));
        iMatched.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rMatched"));

        iTableFiles.setItems(data);
    }

    public void addTableFilesItem(TableFiles item) {
        data.add(item);
        sortTable();
    }

    public void addTableFilesItems(ObservableList<TableFiles> items) {
        data.addAll(items);
        sortTable();
    }

    private void sortTable() {
        Comparator<TableFiles> comparator =
                (TableFiles o1, TableFiles o2)->o2.getRMatched().compareTo(o1.getRMatched());
        ObservableList<TableFiles> sortedData = data.sorted(comparator);
        iTableFiles.setItems(sortedData);
    }

}
