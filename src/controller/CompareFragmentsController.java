package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.CompareFragments;

public class CompareFragmentsController implements Initializable {

    private CompareCodesController compareCodesController;

    @FXML private TableView<CompareFragments> compareFragments;
    @FXML private TableColumn<CompareFragments, String> iFileFragment1;
    @FXML private TableColumn<CompareFragments, String> iFileFragment2;

    private final ObservableList<CompareFragments> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFileFragment1.setCellValueFactory(new PropertyValueFactory<CompareFragments, String>("rFileFragment1"));
        iFileFragment2.setCellValueFactory(new PropertyValueFactory<CompareFragments, String>("rFileFragment2"));

        compareFragments.setItems(data);

        setRowFactory(compareFragments);
    }

    private void setRowFactory(TableView<CompareFragments> tableView) {
        tableView.setRowFactory(e -> {
            TableRow<CompareFragments> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (! tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    CompareFragments compareFragments = tableRow.getItem();
                    compareCodesController.setCodes(compareFragments.getFileMarked1(), compareFragments.getFileMarked2());
                }
            });
            return tableRow ;
        });
    }

    public void setData(ArrayList<CompareFragments> items) {
        data.setAll(items);
    }

    public void setColumnName(int columnNumber, String title) {
        Label headerLabel = new Label(title);

        if (columnNumber == 1) {
            iFileFragment1.setGraphic(headerLabel);
        }
        else if (columnNumber == 2) {
            iFileFragment2.setGraphic(headerLabel);
        }
    }

    public void setCompareCodesController(CompareCodesController compareCodesController) {
        this.compareCodesController = compareCodesController;
    }
}
