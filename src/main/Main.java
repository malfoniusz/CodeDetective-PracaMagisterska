package main;
import java.io.IOException;
import java.util.ArrayList;

import controller.CompareFilesController;
import controller.MainController;
import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.CompareFiles;
import staticc.Compare;
import staticc.PropertiesReader;

public class Main extends Application {

    private final boolean PRELOAD = true;

    private final String SCENE_FILE_NAME = PropertiesReader.readProperty("main_class");
    private final String PROGRAM_NAME = PropertiesReader.readProperty("program_name");

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

        if (PRELOAD) {
            preload();
        }
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
        Image icon = new Image(ClassLoader.getSystemResource(PropertiesReader.readProperty("icon")).toString());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    // TODO: kod testow
    private void preload() {
        ArrayList<CompareFiles> compareFiles = Compare.runCompare();
        CompareFilesController compareFilesController = mainController.getCompareFilesController();
        if (compareFiles != null) {
            compareFilesController.setData(compareFiles);
        }
    }

}
