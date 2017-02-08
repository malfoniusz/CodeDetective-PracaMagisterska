package main;
import java.io.IOException;

import controller.MainController;
import controller.MenuController;
import controller.TableFilesController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.TableFiles;

public class Main extends Application {

    private final String SCENE_FILE_NAME = "../fxml/Main.fxml";
    private final String PROGRAM_NAME = "CodeDetective";

    private MainController mainController;
    private MenuController menuController;
    private TableFilesController tableFilesController;

    private Parent root;

	public static void main(String[] args) {
	    launch(args);
	}

    @Override
    public void start(Stage stage) throws Exception {
        initialize(stage);

        ObservableList<TableFiles> items = FXCollections.observableArrayList(
            new TableFiles("plik45", 274, "Project2", "plik31", 233, 376, 89),
            new TableFiles("plik12", 538, "Project1", "plik32", 466, 254, 23)
        );

        tableFilesController.addTableFilesItems(items);

        prepareStage(stage);
    }

    private void initialize(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SCENE_FILE_NAME));
        root = (Parent) loader.load();

        mainController = loader.getController();
        menuController = mainController.getMenuController();
        tableFilesController = mainController.getTableFilesController();

        menuController.setMainStage(stage);
    }

    private void prepareStage(Stage stage) {
        Scene scene = new Scene(root);
        stage.setTitle(PROGRAM_NAME);
        Image icon = new Image(ClassLoader.getSystemResource("detective.png").toString());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}
