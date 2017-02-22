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
    @FXML private TableColumn<TableFragments, String> iFile1Fragment;
    @FXML private TableColumn<TableFragments, String> iFile2Fragment;

    private final ObservableList<TableFragments> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFile1Fragment.setCellValueFactory(new PropertyValueFactory<TableFragments, String>("rFile1Fragment"));
        iFile2Fragment.setCellValueFactory(new PropertyValueFactory<TableFragments, String>("rFile2Fragment"));

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
                    int fromLine1 = tableFragments.getFile1FromLine();
                    int toLine1 = tableFragments.getFile1ToLine();
                    File file2 = tableFragments.getFile2();
                    int fromLine2 = tableFragments.getFile2FromLine();
                    int toLine2 = tableFragments.getFile2ToLine();

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
