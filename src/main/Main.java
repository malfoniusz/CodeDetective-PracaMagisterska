package main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import controller.Algorithm;
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
import model.CompareFragments;
import model.FileMarked;
import model.Project;
import model.Projects;

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

        testMain();
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

    // TODO: kod testow
    final String PATH = "F:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\_Tests\\";
    final String FILE_1 = "Drawing.java";
    final String FILE_2 = "Game.java";
    private void testMain() {
        Project project = new Project(new File(PATH + FILE_1));
        Projects projects = new Projects(new File(PATH));

        Algorithm algorithm = mainController.getAlgorithm();
        algorithm.getAlgorithmData().setProject(project);
        algorithm.getAlgorithmData().setProjects(projects);

        // Print tokenization
        //AlgorithmData algorithmData = algorithm.getAlgorithmData();
        //System.out.println(Tokenization.toStringTokenization(algorithmData.getProject()));
        //System.out.println(Tokenization.toStringTokenization(algorithmData.getProjects()));

        ArrayList<CompareFiles> compareFiles = algorithm.runAlgorithm();
        //ArrayList<CompareFiles> compareFiles = testCompareFiles();
        CompareFilesController compareFilesController = mainController.getCompareFilesController();
        if (compareFiles != null) {
            compareFilesController.setData(compareFiles);
        }
    }

    private ArrayList<CompareFiles> testCompareFiles() {
        File file1 = new File(PATH + FILE_1);
        File file2 = new File(PATH + FILE_2);

        FileMarked fileMarked1a = new FileMarked(file1, 20, 38);
        FileMarked fileMarked1b = new FileMarked(file1, 30, 40);
        FileMarked fileMarked2a = new FileMarked(file2, 15, 32);
        FileMarked fileMarked2b = new FileMarked(file2, 74, 100);

        ArrayList<CompareFragments> compareFragments1 = new ArrayList<>();
        CompareFragments fragments1 = new CompareFragments(fileMarked1a, fileMarked2a);
        CompareFragments fragments2 = new CompareFragments(fileMarked1b, fileMarked2b);
        compareFragments1.add(fragments1);
        compareFragments1.add(fragments2);

        ArrayList<CompareFragments> compareFragments2 = new ArrayList<>();
        CompareFragments fragments3 = new CompareFragments(fileMarked2a, fileMarked1a);
        CompareFragments fragments4 = new CompareFragments(fileMarked2b, fileMarked1b);
        compareFragments2.add(fragments3);
        compareFragments2.add(fragments4);

        ArrayList<CompareFiles> compareFiles = new ArrayList<>();
        CompareFiles compareFiles1 = new CompareFiles("Project1", file1, 365, file2, 566, 58, compareFragments1);
        CompareFiles compareFiles2 = new CompareFiles("MassiveOnlineUtopia", new File("myPath/plik2"), 205, new File("myPath/plik3"), 566, 95, compareFragments2);
        compareFiles.add(compareFiles1);
        compareFiles.add(compareFiles2);

        return compareFiles;
    }

}
