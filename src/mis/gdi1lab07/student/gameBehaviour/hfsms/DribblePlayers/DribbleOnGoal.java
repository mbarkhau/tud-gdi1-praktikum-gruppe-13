package mis.gdi1lab07.student.gameBehaviour.hfsms.DribblePlayers;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

/**
 * L�sst player in Richtung gegnerisches Tor dribeln
 */
public class DribbleOnGoal<T extends GameEnv> extends StudentHFSM<T> {

	private final FieldPlayer<T> player;

	public DribbleOnGoal(FieldPlayer<T> player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	//TODO Player soll aufs Tor dribblen

}