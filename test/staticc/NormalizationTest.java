package staticc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.tokenization.CodeLine;
import model.tokenization.NormalizedCode;
import utilities.Constants;
import utilities.Utilities;

public class NormalizationTest {

    @Test
    public void test() {
        NormalizedCode normCodeT = Normalization.codeNormalization(Constants.FILE_T);
        NormalizedCode normCodeTNorm = Utilities.loadNormalizedFile(Constants.FILE_T_NORMALIZED);

        for (int i = 0; i < normCodeT.getCodeLines().size(); i++) {
            CodeLine tCodeLine = normCodeT.getCodeLines().get(i);
            CodeLine tNormCodeLine = normCodeTNorm.getCodeLines().get(i);

            assertEquals(tCodeLine.getLineNumber(), tNormCodeLine.getLineNumber());
            assertEquals(tCodeLine.getCode(), tNormCodeLine.getCode());
        }
    }

}
