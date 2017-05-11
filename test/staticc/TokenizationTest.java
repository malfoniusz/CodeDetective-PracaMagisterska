package staticc;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.tokenization.Token;
import model.tokenization.TokenFile;
import model.tokenization.TokenLine;
import utilities.Constants;
import utilities.SettingsTokensUtilities;
import utilities.Utilities;

public class TokenizationTest {

	@Before
	public void setUp() throws Exception {
		// TODO: zapisac i ustawic nowe ustawienia tokenizacji
	}

	@After
	public void tearDown() throws Exception {
		// TODO: przywrocic ustawienia
	}

	@Test
	public void test() throws IOException {
		SettingsTokensUtilities.tokenizationTypeFull();

		TokenFile tokenFileT = Tokenization.tokenization(Constants.FILE_T);
		TokenFile tokenFileTTokenized = Utilities.loadTokenizedFile(Constants.FILE_T_TOKENIZED);

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
