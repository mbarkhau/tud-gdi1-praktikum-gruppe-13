package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

public class AnouncePass<T> extends StudentHFSM<T> {
	
	private final FieldPlayer player;
	
	public AnouncePass(FieldPlayer player){
		this.player = player;
		
	}
}
