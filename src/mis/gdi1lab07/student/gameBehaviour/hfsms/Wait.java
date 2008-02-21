package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** Warte auf Spielbeginn. */
public class Wait<T> extends StudentHFSM<T> {

	private final FieldPlayer player;
	
	public Wait(FieldPlayer player){
		super();
		this.player = player;
	}
}
