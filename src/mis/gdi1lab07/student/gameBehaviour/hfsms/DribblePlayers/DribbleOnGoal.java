package mis.gdi1lab07.student.gameBehaviour.hfsms.DribblePlayers;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/**
 * Lässt player in Richtung gegnerisches Tor dribeln
 */
public class DribbleOnGoal<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public DribbleOnGoal(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	//TODO Player soll aufs Tor dribblen

}