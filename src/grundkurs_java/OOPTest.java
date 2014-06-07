package grundkurs_java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import grundkurs_java.OOP.*;

public class OOPTest {

	private Human t;

	@Before
	public void setUp() throws Exception {
		t = new Human(16, "John", "Locke", true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHumanAge() {
		assertEquals("age should be 16.", 16, t.getAge());
		// fail("Not yet implemented");
	}

	@Test
	public void testHumanCounter() {
		assertEquals(1, Human.getCounter());
	}
}
