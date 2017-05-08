package staticc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.tokenization.NormalizedCode;
import utilities.Constants;

public class NormalizationTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        // TODO: sczytac kod z pliku P1_Normalized do obiektu NormalizedCode i porownac oba obiekty (najlepiej linijka po linijce)

        NormalizedCode normCode = Normalization.codeNormalization(Constants.FILE_P1);

        System.out.println(normCode.toString());

        assertEquals(1, 1);
    }

}
