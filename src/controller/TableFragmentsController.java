package controller;

import java.io.File;
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
import model.TableFragments;

public class TableFragmentsController implements Initializable {

    private CodesViewController codesViewController;

    @FXML private TableView<TableFragments> tableFragments;
    @FXML private TableColumn<TableFragments, String> iFileFragment1;
    @FXML private TableColumn<TableFragments, String> iFileFragment2;

    private final ObservableList<TableFragments> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFileFragment1.setCellValueFactory(new PropertyValueFactory<TableFragments, String>("rFileFragment1"));
        iFileFragment2.setCellValueFactory(new PropertyValueFactory<TableFragments, String>("rFileFragment2"));

        tableFragments.setItems(data);

        setRowFactory(tableFragments);
    }

    private void setRowFactory(TableView<TableFragments> tableView) {
        tableView.setRowFactory(e -> {
            TableRow<TableFragments> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (! tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    TableFragments tableFragments = tableRow.getItem();

                    File file1 = tableFragments.getFile1();
                    int fromLine1 = tableFragments.getFromLine1();
                    int toLine1 = tableFragments.getToLine1();
                    File file2 = tableFragments.getFile2();
                    int fromLine2 = tableFragments.getFromLine2();
                    int toLine2 = tableFragments.getToLine2();

                    codesViewController.setCodes(file1, fromLine1, toLine1, file2, fromLine2, toLine2);
                }
            });
            return tableRow ;
        });
    }

    public void setData(ArrayList<TableFragments> items) {
        data.setAll(items);
    }

    public void setCodesViewController(CodesViewController codesViewController) {
        this.codesViewController = codesViewController;
    }
}
