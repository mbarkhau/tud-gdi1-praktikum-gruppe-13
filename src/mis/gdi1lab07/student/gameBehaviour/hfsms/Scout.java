package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

public class Scout<T> extends StudentHFSM<T> {
	
	private final FieldPlayer player;
	
	public Scout(FieldPlayer player){
		
		this.player = player;
		this.setName(getClass().getName());
	}
}
