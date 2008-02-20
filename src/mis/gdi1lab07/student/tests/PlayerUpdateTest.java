package mis.gdi1lab07.student.tests;

import mis.gdi1lab07.student.gameBehaviour.Controller;

import org.junit.Before;

import atan2.model.ControllerAdaptor;
import atan2.model.Player;

/**
 * Tests the callback methods of the Controller, and makes sure the FieldPlayer
 * is updated correctly.
 */
public class PlayerUpdateTest {

	@Before
	public void setUp() throws Exception {
		Player player = new DummyPlayerImpl(0);
//		ControllerAdaptor controller = new Controller("TestTeam", player);
		
	}

}
