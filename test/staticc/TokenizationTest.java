package staticc;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.tokenization.Token;
import model.tokenization.TokenFile;
import model.tokenization.TokenLine;
import utilities.Constants;
import utilities.Utilities;
import utilities.settings.SettingsLoadSave;
import utilities.settings.SettingsTokensUtilities;
import utilities.settings.model.SettingsAll;

public class TokenizationTest {

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
	public void full() {
		SettingsTokensUtilities.tokenizationTypeFull();
		testTokenization(Constants.FILE_T_TOKENIZED_FULL);
	}

	@Test
    public void normal() {
        SettingsTokensUtilities.tokenizationTypeNormal();
        testTokenization(Constants.FILE_T_TOKENIZED_NORMAL);
    }

	@Test
    public void minimalistic() {
        SettingsTokensUtilities.tokenizationTypeMinimalistic();
        testTokenization(Constants.FILE_T_TOKENIZED_MINIMALISTIC);
    }

	private void testTokenization(File tokenizedFile) {
	    TokenFile tokenFileT = Tokenization.tokenization(Constants.FILE_T);
        TokenFile tokenFileTTokenized = Utilities.loadTokenizedFile(tokenizedFile);

        for (int A = 0; A < tokenFileT.getTokenLines().size(); A++) {
            TokenLine tTokenLine = tokenFileT.getTokenLines().get(A);
            TokenLine tTokenizedTokenLine = tokenFileTTokenized.getTokenLines().get(A);

            assertEquals(tTokenLine.getLineNumber(), tTokenizedTokenLine.getLineNumber());

            for (int B = 0; B < tTokenLine.getTokens().size(); B++) {
                Token tToken = tTokenLine.getTokens().get(B);
                Token tTokenizedToken = tTokenizedTokenLine.getTokens().get(B);

                assertEquals(tToken, tTokenizedToken);
            }
        }
	}

}
