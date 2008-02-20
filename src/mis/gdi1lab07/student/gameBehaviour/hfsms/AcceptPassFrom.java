package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** Passpiel Annahme bestätigen. */
public class AcceptPassFrom<T> extends StudentHFSM<T> {
	
	private final FieldPlayer player;
	
	public AcceptPassFrom(FieldPlayer player){
		this.player = player;
		
	}
}
