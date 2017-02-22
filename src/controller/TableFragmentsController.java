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
                    codesViewController.setCodes(tableFragments.getFileMarked1(), tableFragments.getFileMarked2());
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
