package grundkurs_java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import grundkurs_java.OOP.*;

public class OOPTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHumanAge() {
		Human h = new Human(16, "John", "Locke", true);
		assertEquals("age should be 16.", 16, h.getAge());
	}

	@Test
	public void testHumanCounter() {
		Human h = new Human(16, "John", "Locke", true);
		assertEquals(1, Human.getCounter());
	}

	@Test
	public void testPoint() {
		Point p = new Point(10.14, 37.2);
		assertEquals(10.14, p.getX(), 0.00001);
		assertEquals(37.2, p.getY(), 0.00001);
		assertEquals("10.14,37.2", p.toString());
	}

	@Test
	public void testDistance() {
		Point a = new Point(-3, 5);
		Point b = new Point(7, -1);
		Distance d = new Distance(a, b);
		assertEquals(11.6, d.getLength(), 0.1);
	}

	@Test
	public void testPatient() {
		Patient maier = new Patient("H. Maier", 68);
		Patient schmid = new Patient("G. Schmid", 45, maier);
		Patient berger = new Patient("I. Berger", 36, schmid);
		assertEquals(4, Patient.nextNumber);
		assertEquals(3, berger.getNumber());
		assertTrue("maier is first", maier.isFirst());
		assertFalse("berger is not first", berger.isFirst());
		assertEquals("G. Schmid (2) is after H. Maier (1)", schmid.toString());
	}
}
