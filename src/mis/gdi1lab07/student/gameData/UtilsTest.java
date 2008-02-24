package mis.gdi1lab07.student.gameData;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

	FieldVector v1;
	FieldVector v2;
	FieldVector v3;
	FieldVector v4;
	FieldVector v5;
	
	@Before
	public void setUp() throws Exception {
		v1 = new FieldVector(10, 160);
		v2 = new FieldVector(5.656, 45);
		v3 = new FieldVector(10, -70);
		v4 = new FieldVector(10, 0);
		v5 = new FieldVector(10, -179.9);
	}

	@Test
	public void testTurnVector(){
		Utils.turnVector(-40, v1);
		assertTrue(v1.getDirection() == -160);
		Utils.turnVector(20, v2);
		assertTrue(v2.getDirection() == 25);
		Utils.turnVector(-120, v3);
		assertTrue(v3.getDirection() == 50);
		Utils.turnVector(90, v3);
		assertTrue(v3.getDirection() == -40);
	}
	
	@Test
	public void testDisplaceVector(){
		
		Utils.displaceVector(800, v2);
		System.out.println(v3);
		assertTrue(Utils.inDelta(135, v2.getDirection(), 0.1));
		assertTrue(Utils.inDelta(v2.getDistance(), 5.656, 0.1));
		
		Utils.displaceVector(100, v3);
		System.out.println(v3);
		assertTrue(Utils.inDelta(-75.55, v3.getDirection(), 0.1));
		assertTrue(Utils.inDelta(v3.getDistance(), 9.697, 0.1));
		
		Utils.displaceVector(100, v4);
		System.out.println(v4);
		assertTrue(Utils.inDelta(0, v4.getDirection(), 0.1));
		assertTrue(Utils.inDelta(v4.getDistance(), 9, 0.1));
		
		Utils.displaceVector(100, v5);
		System.out.println(v5);
		assertTrue(Utils.inDelta(-179.9, v5.getDirection(), 0.1));
		assertTrue(Utils.inDelta(v5.getDistance(), 11, 0.1));
	}
}
