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
}
