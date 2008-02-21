package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensivePlayers;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** Grundstellung des Offensiv-Spieler,
 * bei der bei Spielbeginn eine Transition feuern sollte:
 * Entweder sieht der Player den Ball nicht oder er sieht ihn
 * und befindet sich am n�chsten zum Ball, in beiden F�llen wird er zum Dribble-Player.
 * 
 * Sieht er dass ein Mitspieler n�her zum Ball steht st�rmt er ohne Ball nach vorne.
 */
public class LookAhead<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public LookAhead(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}

}
