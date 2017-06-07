package main;
import java.io.File;
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
import model.Project;
import model.Projects;
import staticc.Compare;
import staticc.PropertiesReader;
import staticc.Settings;

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
    final String PATH_PROJECT = "F:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\_Project\\";
    final String PATH_BASE = "F:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\_Base\\";
    private void preload() {
        Project project = new Project(new File(PATH_PROJECT));
        Projects base = new Projects(new File(PATH_BASE));

        Settings.setProject(project);
        Settings.setBase(base);

        ArrayList<CompareFiles> compareFiles = Compare.runCompare();
        CompareFilesController compareFilesController = mainController.getCompareFilesController();
        if (compareFiles != null) {
            compareFilesController.setData(compareFiles);
        }
    }

}
