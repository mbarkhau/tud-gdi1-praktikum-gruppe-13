package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensivePlayers;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/**
 * Scouted nur den Ball, d.h. wenn der Player ihn gefunden hat, hört er auf sich zu drehen
 */
public class ScoutForBall<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public ScoutForBall(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}

}
