package main;
import controller.MainController;
import controller.TableFilesController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.TableFiles;

public class Main extends Application {

    private final String SCENE_FILE_NAME = "../fxml/Main.fxml";

    private MainController mainController;
    private TableFilesController tableFilesController;

	public static void main(String[] args) {
	    launch(args);
	}

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SCENE_FILE_NAME));
        Parent root = (Parent) loader.load();
        mainController = loader.getController();
        tableFilesController = mainController.getTableFilesController();

        ObservableList<TableFiles> items = FXCollections.observableArrayList(
            new TableFiles("plik45", 274, "Project2", "plik31", 233, 376, 89),
            new TableFiles("plik12", 538, "Project1", "plik32", 466, 254, 23)
        );

        tableFilesController.addTableFilesItems(items);

        Scene scene = new Scene(root);
        primaryStage.setTitle("My title");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
