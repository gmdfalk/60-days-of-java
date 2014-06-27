package grundkurs_java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OOPAdvancedTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        CurrencyCalc calc = new CurrencyCalc(123.4);
        assertEquals(63.09, calc.inEuro(), 0.1);
        assertEquals(413.8, calc.inFranc(), 0.1); //a
        assertEquals(122165.89274118916, calc.inLire(), 0.0000000001);
    }

}
