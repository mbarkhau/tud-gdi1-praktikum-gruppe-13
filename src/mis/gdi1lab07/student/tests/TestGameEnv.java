package mis.gdi1lab07.student.tests;

import static org.junit.Assert.assertTrue;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

import org.junit.Before;
import org.junit.Test;

public class TestGameEnv {
	
	GameEnv env = new GameEnv();
	
	@Before
	public void setUp() throws Exception {
		env.setBall(new FieldVector(20, 50));
	}

	@Test
	public void testTurn() {
		FieldVector ball = env.getBall();
		env.turn(20);
		assertTrue(ball.getDirection() == 30);
		env.turn(180);
		assertTrue(ball.getDirection() == -150);
		env.turn(-230);
		System.out.println(ball);
		assertTrue(ball.getDirection() == 80);
	}

}
