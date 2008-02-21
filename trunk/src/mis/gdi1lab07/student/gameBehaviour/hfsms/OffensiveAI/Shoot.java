package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/**
 * Lässt player aufs Tor schiessen
 */
public class Shoot<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public Shoot(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	//TODO Player soll aufs Tor schiessen

}