package main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.nlputil.gst.GreedyStringTiling;
import com.nlputil.gst.MatchVals;
import com.nlputil.gst.PlagResult;

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
import staticc.Compare;
import staticc.Settings;

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
    final String PATH_PROJECT = "D:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\_Tests\\Drawing\\";
    final String PATH_BASE = "D:\\Documents\\_Praca magisterska\\ProjektyDoTestow\\_Tests\\";
    private void testMain() {
        Project project = new Project(new File(PATH_PROJECT));
        Projects base = new Projects(new File(PATH_BASE));

        Settings.setProject(project);
        Settings.setBase(base);

        // Print tokenization
        //System.out.println(Tokenization.toStringTokenization(Settings.getProject()));
        //System.out.println(Tokenization.toStringTokenization(Settings.getBase()));

        ArrayList<CompareFiles> compareFiles = Compare.runCompare();
        //ArrayList<CompareFiles> compareFiles = testCompareFiles();
        CompareFilesController compareFilesController = mainController.getCompareFilesController();
        if (compareFiles != null) {
            compareFilesController.setData(compareFiles);
        }

        testGreedyStringTiling();
    }

    private void testGreedyStringTiling() {
//        String pattern = "with Hash table entries Hash table entries has Arun name is here, Arun name is here with Hash table entries Arun how is arun";
//        String text = "Hash table entries has Arun name is here, Arun name is here with Hash table entries Arun how is arun Arun name is here with Hash table entries";
        String pattern = "a b c d c b a d c b a d c b a d b";
        String text = "a b c d d c b a d a c d a d c d a d b b b d a c b b d a d c b d a";
//        String pattern = "a b c d";
//        String text = "a b c d";

        PlagResult result = GreedyStringTiling.run(pattern, text, 2, (float)0.5, false);
        System.out.println("Similarity: "+result.getSimilarity());
        System.out.print("Plagiriasm tiles: ");
        for (MatchVals tiles : result.getTiles()){
            System.out.print("(" + tiles.patternPostion + "," + tiles.textPosition + "," + tiles.length + ")");
        }
    }

    // Nie działa, trzeba stworzyć pliki jako Project i Projects
    final String FILE_1 = "Drawing.java";
    final String FILE_2 = "Game.java";
    private ArrayList<CompareFiles> testCompareFiles() {
        File file1 = new File(PATH_PROJECT + FILE_1);
        File file2 = new File(PATH_BASE + FILE_2);

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
        CompareFiles compareFiles1 = new CompareFiles("Project1", file1, 365, "Base1", file2, 566, 58, compareFragments1);
        CompareFiles compareFiles2 = new CompareFiles("Ultima", new File("myPath/plik2"), 205, "MassiveOnlineUtopia", new File("myPath/plik3"), 566, 95, compareFragments2);
        compareFiles.add(compareFiles1);
        compareFiles.add(compareFiles2);

        return compareFiles;
    }

}
