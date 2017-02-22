package main;
import java.io.IOException;

import controller.MainController;
import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private final String SCENE_FILE_NAME = "../fxml/Main.fxml";
    private final String PROGRAM_NAME = "CodeDetective";

    private MainController mainController;
    private MenuController menuController;

    private Parent root;

	public static void main(String[] args) {
	    launch(args);
	}

    @Override
    public void start(Stage stage) throws Exception {
        initialize(stage);
        prepareStage(stage);
    }

    private void initialize(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SCENE_FILE_NAME));
        root = (Parent) loader.load();

        mainController = loader.getController();

        menuController = mainController.getMenuController();
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
