package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** Passpiel Annahme anfordern. */
public class RequestPassTo<T> extends StudentHFSM<T> {
	
	private final FieldPlayer player;
	
	public RequestPassTo(FieldPlayer player){
		this.player = player;
		
	}
}
