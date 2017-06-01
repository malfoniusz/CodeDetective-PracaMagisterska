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
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.CompareFiles;

public class CompareFilesController implements Initializable {

    private CompareFragmentsController compareFragmentsController;
    private CompareCodesController compareCodesController;

    @FXML private TableView<CompareFiles> compareFiles;
    @FXML private TableColumn<CompareFiles, String> iFileProject;
    @FXML private TableColumn<CompareFiles, String> iFileBase;
    @FXML private TableColumn<CompareFiles, String> iBaseName;
    @FXML private TableColumn<CompareFiles, Integer> iLongestMatch;
    @FXML private TableColumn<CompareFiles, Float> iSimilarity; // <0, 100>

    private final ObservableList<CompareFiles> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iFileProject.setCellValueFactory(new PropertyValueFactory<CompareFiles, String>("rFileProject"));
        iFileBase.setCellValueFactory(new PropertyValueFactory<CompareFiles, String>("rFileBase"));
        iBaseName.setCellValueFactory(new PropertyValueFactory<CompareFiles, String>("rBaseName"));
        iLongestMatch.setCellValueFactory(new PropertyValueFactory<CompareFiles, Integer>("rLongestMatch"));
        iSimilarity.setCellValueFactory(new PropertyValueFactory<CompareFiles, Float>("rSimilarity"));

        compareFiles.setPlaceholder(new Label("From menu:\nChoose Base\nChoose Project\nPress Start"));
        compareFiles.setItems(data);

        setRowFactory(compareFiles);
    }

    private void setRowFactory(TableView<CompareFiles> tableView) {
        tableView.setRowFactory(e -> {
            TableRow<CompareFiles> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (! tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    CompareFiles compareFiles = tableRow.getItem();
                    compareFragmentsController.setShortDir(compareFiles.getFileProjectShortPath(), compareFiles.getFileBaseShortPath());
                    compareFragmentsController.setData(compareFiles.getCompareFragments());
                    compareCodesController.clearCodes();
                    compareFragmentsController.selectFirst();
                }
            });
            return tableRow ;
        });
    }

    public void setData(ArrayList<CompareFiles> items) {
        data.setAll(items);

        if (compareFiles.getSortOrder().isEmpty()) {
        	iSimilarity.setSortType(SortType.DESCENDING);
        	compareFiles.getSortOrder().add(iSimilarity);
        }
        compareFiles.sort();
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
