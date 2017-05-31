package staticc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nlputil.gst.GreedyStringTiling;
import com.nlputil.gst.MatchVals;
import com.nlputil.gst.PlagResult;

import model.CompareFiles;
import model.CompareFragments;
import model.SetttingsTokensRadioGroup;
import utilities.Constants;
import utilities.settings.SettingsLoadSave;
import utilities.settings.model.SettingsAll;

public class CompareTest {

	public static SettingsAll settingsAll;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		settingsAll = SettingsLoadSave.loadSettings();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SettingsLoadSave.saveSettings(settingsAll);
	}

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
                assertEquals(tile.textPosition, 12);
                assertEquals(tile.patternPostion, 11);
                assertEquals(tile.length, 3);
                break;
            case 2:
                assertEquals(tile.textPosition, 16);
                assertEquals(tile.patternPostion, 15);
                assertEquals(tile.length, 3);
                break;
            }
        }

        assertEquals(result.similarity, 0.43137255, 0.01);
    }

    @Test
    public void compare() {
        SettingsLoadSave.setSettings(Constants.PROJECT_FOLDER, Constants.BASE_FOLDER, 5, 0.01, SetttingsTokensRadioGroup.NORMAL);

        ArrayList<CompareFiles> compareFiles = Compare.runCompare();

        for (int A = 0; A < compareFiles.size(); A++) {
            CompareFiles compareFile = compareFiles.get(A);

            switch (A) {
            case 0:
                assertCompareFile(compareFile, "P1.java", 109, "B1.java", 101, "base1", 34, 94.37, ".\\P1.java", ".\\base1\\B1.java");

                for (int B = 0; B < compareFile.getCompareFragments().size(); B++) {
                    CompareFragments compareFragment = compareFile.getCompareFragments().get(B);
                    switch (B) {
                    case 0:
                        assertCompareFragment(compareFragment, "P1.java", 3, 46, "B1.java", 3, 46);
                        break;
                    case 1:
                        assertCompareFragment(compareFragment, "P1.java", 59, 109, "B1.java", 51, 101);
                        break;
                    }
                }
                break;
            case 1:
                assertCompareFile(compareFile, "P1.java", 109, "B2.java", 248, "base2", 11, 8.84, ".\\P1.java", ".\\base2\\B2.java");

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
        assertEquals(compareFragment.getProjectFile().getName(), fileProjectName);
        assertEquals(compareFragment.getRProjectFrom(), pFromLine);
        assertEquals(compareFragment.getRProjectTo(), pToLine);
        assertEquals(compareFragment.getBaseFile().getName(), fileBaseName);
        assertEquals(compareFragment.getRBaseFrom(), bFromLine);
        assertEquals(compareFragment.getRBaseTo(), bToLine);
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
