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
	public void testCurrency() {
		CurrencyCalc calc = new CurrencyCalc(123.4);
		assertEquals(63.09, calc.inEuro(), 0.1);
		assertEquals(413.8, calc.inFranc(), 0.1); // a
		assertEquals(122165.89274118916, calc.inLire(), 0.0000000001);
	}

	@Test
	public void testMetallplatte() {
		GelochtePlatte gp = new GelochtePlatte(3, 5, 2);
		assertEquals("width", 5, gp.breite, 0.1);
		assertEquals("length", 3, gp.laenge, 0.1);
		assertEquals("area ohne löcher", 7.5, gp.flaeche(), 0.1);
		assertEquals("anzahl löcher", gp.getAnzahlLoecher(), 2);
		assertEquals("lochbreite", 2.5, gp.getLochBreite(), 0.1);
		assertEquals("lochlänge", 1.5, gp.getLochLaenge(), 0.1);
	}
}
