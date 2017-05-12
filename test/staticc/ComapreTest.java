package staticc;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.nlputil.gst.GreedyStringTiling;
import com.nlputil.gst.MatchVals;
import com.nlputil.gst.PlagResult;

import model.CompareFiles;
import model.CompareFragments;
import model.FileMarked;
import model.Project;
import model.Projects;
import model.SetttingsTokensRadioGroup;
import utilities.Constants;
import utilities.SettingsTokensUtilities;

public class ComapreTest {

    @Test
    public void algorithm() {
        final String TEXT = "a b c d d c b a d a c d a d c d a d b b b d a c b b d a d c b d a";
        final String PATTERN = "a a b c d c b a d c b a d c b a d b";

        PlagResult result = GreedyStringTiling.run(PATTERN, TEXT, 2, (float)0.5, false);

        for (int i = 0; i < result.tiles.size(); i++) {
            MatchVals tile = result.tiles.get(i);

            switch(i) {
            case 0:
                assertEquals(tile.textPosition, 4);
                assertEquals(tile.patternPostion, 4);
                assertEquals(tile.length, 5);
                break;
            case 1:
                assertEquals(tile.textPosition, 4);
                assertEquals(tile.patternPostion, 8);
                assertEquals(tile.length, 5);
                break;
            case 2:
                assertEquals(tile.textPosition, 4);
                assertEquals(tile.patternPostion, 12);
                assertEquals(tile.length, 5);
                break;
            case 3:
                assertEquals(tile.textPosition, 0);
                assertEquals(tile.patternPostion, 1);
                assertEquals(tile.length, 3);
                break;
            }
        }
    }

    @Test
    public void compare() {
        setSettings(Constants.PROJECT_FOLDER, Constants.BASE_FOLDER, 5, SetttingsTokensRadioGroup.NORMAL);

        ArrayList<CompareFiles> compareFiles = Compare.runCompare();

        for (int A = 0; A < compareFiles.size(); A++) {
            CompareFiles compareFile = compareFiles.get(A);

            switch (A) {
            case 0:
                assertCompareFile(compareFile, "P1.java", 109, "B1.java", 101, "base1", 35, 95.77, ".\\project\\P1.java", ".\\base1\\B1.java");

                for (int B = 0; B < compareFile.getCompareFragments().size(); B++) {
                    CompareFragments compareFragment = compareFile.getCompareFragments().get(B);
                    switch (B) {
                    case 0:
                        assertCompareFragment(compareFragment, "P1.java", 3, 46, "B1.java", 3, 46);
                        break;
                    case 1:
                        assertCompareFragment(compareFragment, "P1.java", 58, 109, "B1.java", 50, 101);
                        break;
                    }
                }
                break;
            case 1:
                assertCompareFile(compareFile, "P1.java", 109, "B2.java", 248, "base2", 11, 8.84, ".\\project\\P1.java", ".\\base2\\B2.java");

                for (int B = 0; B < compareFile.getCompareFragments().size(); B++) {
                    CompareFragments compareFragment = compareFile.getCompareFragments().get(B);
                    switch (B) {
                    case 0:
                        assertCompareFragment(compareFragment, "P1.java", 82, 97, "B2.java", 112, 126);
                        break;
                    }
                }
                break;
            }
        }
    }

    private void setSettings(File fileProject, File fileBase, int minimalLines, SetttingsTokensRadioGroup tokenizationType) {
        Project project = new Project(fileProject);
        Projects base = new Projects(fileBase);

        Settings.setProject(project);
        Settings.setBase(base);
        Settings.setMinimalMatchedLinesValue(minimalLines);

        if (tokenizationType.equals(SetttingsTokensRadioGroup.NORMAL)) {
            SettingsTokensUtilities.tokenizationTypeNormal();
        }
    }

    private void assertCompareFile(CompareFiles compareFile, String fileProjectName, int fileProjectLines, String fileBaseName,
            int fileBaseLines, String dirBaseName, int longestMatch, double similarity, String projectShortPath, String baseShortPath) {
        assertEquals(compareFile.getFileProject().getName(), fileProjectName);
        assertEquals(compareFile.getFileProjectLines(), fileProjectLines);
        assertEquals(compareFile.getFileBase().getName(), fileBaseName);
        assertEquals(compareFile.getFileBaseLines(), fileBaseLines);
        assertEquals(compareFile.getRBaseName(), dirBaseName);
        assertEquals(compareFile.getRLongestMatch(), longestMatch);
        assertEquals(compareFile.getRSimilarity(), similarity, 0.01);
        assertEquals(compareFile.getFileProjectShortPath(), projectShortPath);
        assertEquals(compareFile.getFileBaseShortPath(), baseShortPath);
    }

    private void assertCompareFragment(CompareFragments compareFragment, String fileProjectName, int pFromLine, int pToLine, String fileBaseName, int bFromLine, int bToLine) {
        FileMarked markedProject = compareFragment.getFileMarkedProject();
        FileMarked markedBase = compareFragment.getFileMarkedBase();

        assertEquals(markedProject.getFile().getName(), fileProjectName);
        assertEquals(markedProject.getFromLine(), pFromLine);
        assertEquals(markedProject.getToLine(), pToLine);
        assertEquals(markedBase.getFile().getName(), fileBaseName);
        assertEquals(markedBase.getFromLine(), bFromLine);
        assertEquals(markedBase.getToLine(), bToLine);
    }

    @SuppressWarnings("unused")
    private void printResult(PlagResult result) {
        System.out.println("Similarity: "+result.getSimilarity());
        System.out.print("Plagiriasm tiles: ");
        for (MatchVals tiles : result.getTiles()){
            System.out.print("(" + tiles.textPosition + "," + tiles.patternPostion + "," + tiles.length + ")");
        }
    }

}
