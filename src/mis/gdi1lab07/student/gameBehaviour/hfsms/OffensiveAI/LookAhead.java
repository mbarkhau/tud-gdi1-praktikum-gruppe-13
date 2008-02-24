package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** 
 * Zwischenzustand der sofort feuert,
 * wenn Spiel l�uft.
 * Hat keine Funktionen.
 * 
 */
public class LookAhead<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public LookAhead(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}

}