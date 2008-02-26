package mis.gdi1lab07.student.tests;


import static org.junit.Assert.assertTrue;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameMessages;
import mis.gdi1lab07.student.gameData.Utils;

import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

	FieldVector v1;
	FieldVector v2;
	FieldVector v3;
	FieldVector v4;
	FieldVector v5;
	FieldVector v6;
	
	@Before
	public void setUp() throws Exception {
		v1 = new FieldVector(10, 160);
		v2 = new FieldVector(5.656, 45);
		v3 = new FieldVector(10, -70);
		v4 = new FieldVector(10, 0);
		v5 = new FieldVector(10, -179.9);
		v6 = new FieldVector(1, 0);
	}

	@Test
	public void testTurnVector(){
		Utils.turnVector(-40, v1);
		assertTrue(v1.getDir() == -160);
		Utils.turnVector(20, v2);
		assertTrue(v2.getDir() == 25);
		Utils.turnVector(-120, v3);
		assertTrue(v3.getDir() == 50);
		Utils.turnVector(90, v3);
		assertTrue(v3.getDir() == -40);
	}
	
	@Test
	public void testDisplaceVector(){
		
		Utils.displaceVector(800, v2);
		assertTrue(Utils.inDelta(135, v2.getDir(), 0.1));
		assertTrue(Utils.inDelta(v2.getDist(), 5.656, 0.1));
		
		Utils.displaceVector(100, v3);
		assertTrue(Utils.inDelta(-75.55, v3.getDir(), 0.1));
		assertTrue(Utils.inDelta(v3.getDist(), 9.697, 0.1));
		
		Utils.displaceVector(100, v4);
		assertTrue(Utils.inDelta(0, v4.getDir(), 0.1));
		assertTrue(Utils.inDelta(v4.getDist(), 9, 0.1));
		
		Utils.displaceVector(100, v5);
		assertTrue(Utils.inDelta(-179.9, v5.getDir(), 0.1));
		assertTrue(Utils.inDelta(v5.getDist(), 11, 0.1));
		
		Utils.displaceVector(300, v6);
		assertTrue(Utils.inDelta(180, v6.getDir(), 0.1));
		assertTrue(Utils.inDelta(v6.getDist(), 2, 0.1));
		
//		System.out.println(v1);
	}
	
	@Test
	public void testInDelta(){
		assertTrue(Utils.inDelta(2, 3.99));
		assertTrue(Utils.inDelta(2, 4, 3));
		assertTrue(Utils.inDelta(-4, -5, 3));
		assertTrue(Utils.inDelta(-1, 1, 3));
		assertTrue(Utils.inDelta(1, -1, 3));
	}
	
	@Test
	public void testFindPlayerId(){
		assertTrue(1 == Utils.findPlayerId(GameMessages.PASS_ACK + 1));
		assertTrue(2 == Utils.findPlayerId(GameMessages.PASS_RESPONSE + 2));
		assertTrue(22 == Utils.findPlayerId(GameMessages.PASS_ACK + 22));
	}
	
	@Test
	public void testPredictVector(){
		FieldVector a1 = new FieldVector(3, 10);
		FieldVector a2 = new FieldVector(2, 10);
		System.out.println(Utils.predictVector(a1, a2, 1));

	}
}
