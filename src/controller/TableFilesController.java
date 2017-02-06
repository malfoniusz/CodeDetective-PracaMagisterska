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
import model.TableFiles;

public class TableFilesController implements Initializable {

    @FXML TableView<TableFiles> iTableFiles;
    @FXML TableColumn<TableFiles, String> iFile1;
    @FXML TableColumn<TableFiles, String> iProject;
    @FXML TableColumn<TableFiles, String> iFile2;
    @FXML TableColumn<TableFiles, Integer> iLines;
    @FXML TableColumn<TableFiles, String> iMatched;

    final ObservableList<TableFiles> data = FXCollections.observableArrayList(
        new TableFiles("MySuperClassController", 365, "Project1", "MainSourceController", 566, 222267, 58),
        new TableFiles("plik2", 205, "MassiveOnlineUtopia", "plik3", 566, 144187, 95)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFile1.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rFile1"));
        iProject.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rProject"));
        iFile2.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rFile2"));
        iLines.setCellValueFactory(new PropertyValueFactory<TableFiles, Integer>("rLines"));
        iMatched.setCellValueFactory(new PropertyValueFactory<TableFiles, String>("rMatched"));

        iTableFiles.setItems(data);
    }

    public void addTableFilesItem(TableFiles item) {
        data.add(item);
        sortTableByMatched();
    }

    public void addTableFilesItems(ObservableList<TableFiles> items) {
        data.addAll(items);
        sortTableByMatched();
    }

    private void sortTableByMatched() {
        iTableFiles.getSortOrder().add(iMatched);
    }

    public ObservableList<TableFiles> getData() {
        return data;
    }

    public void setData(ObservableList<TableFiles> value) {
        data.setAll(value);
    }

}
