package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.CompareFragments;

public class CompareFragmentsController implements Initializable {

    private CompareCodesController compareCodesController;

    @FXML private ListView<String> viewShortDir;
    private final ObservableList<String> shortDir = FXCollections.observableArrayList();

    @FXML private TableView<CompareFragments> compareFragments;
    @FXML private TableColumn<CompareFragments, Integer> iProjectFrom;
    @FXML private TableColumn<CompareFragments, Integer> iProjectTo;
    @FXML private TableColumn<CompareFragments, Integer> iProjectLength;
    @FXML private TableColumn<CompareFragments, Integer> iBaseFrom;
    @FXML private TableColumn<CompareFragments, Integer> iBaseTo;
    @FXML private TableColumn<CompareFragments, Integer> iBaseLength;
    private final ObservableList<CompareFragments> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iProjectFrom.setCellValueFactory(new PropertyValueFactory<CompareFragments, Integer>("rProjectFrom"));
        iProjectTo.setCellValueFactory(new PropertyValueFactory<CompareFragments, Integer>("rProjectTo"));
        iProjectLength.setCellValueFactory(new PropertyValueFactory<CompareFragments, Integer>("rProjectLength"));

        iBaseFrom.setCellValueFactory(new PropertyValueFactory<CompareFragments, Integer>("rBaseFrom"));
        iBaseTo.setCellValueFactory(new PropertyValueFactory<CompareFragments, Integer>("rBaseTo"));
        iBaseLength.setCellValueFactory(new PropertyValueFactory<CompareFragments, Integer>("rBaseLength"));

        viewShortDir.setItems(shortDir);

        compareFragments.setPlaceholder(new Label(""));
        compareFragments.setItems(data);

        setRowFactory(compareFragments);
    }

    private void setRowFactory(TableView<CompareFragments> tableView) {
        tableView.setRowFactory(e -> {
            TableRow<CompareFragments> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (! tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    CompareFragments compareFragments = tableRow.getItem();
                    compareCodesController.setCodes(compareFragments.getFileMarkedProject(), compareFragments.getFileMarkedBase());
                }
            });
            return tableRow ;
        });
    }

    public void setData(ArrayList<CompareFragments> items) {
        data.setAll(items);
    }

    public void clearData() {
        shortDir.clear();
        data.clear();
    }

    public void setShortDir(String projectShortDir, String baseShortDir) {
        String projectStr = "Project:\t" + projectShortDir;
        String baseStr = "Base:\t" + baseShortDir;

        shortDir.setAll(projectStr, baseStr);
    }

    public void setCompareCodesController(CompareCodesController compareCodesController) {
        this.compareCodesController = compareCodesController;
    }
}
