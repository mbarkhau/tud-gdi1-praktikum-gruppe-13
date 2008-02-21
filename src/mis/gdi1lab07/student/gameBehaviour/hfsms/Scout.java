package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

public class Scout<T> extends StudentHFSM<T> {
	
	private final FieldPlayer player;
	
	public Scout(FieldPlayer player){
		super();
		this.player = player;
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		player.turn(72);
	}
}
