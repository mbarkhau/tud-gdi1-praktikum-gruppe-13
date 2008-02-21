package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

public class GoToStartPosition<T> extends StudentHFSM<T> {

	private FieldPlayer player;
	
	public GoToStartPosition(FieldPlayer player) {
		super();
		this.player = player;
	}
	
	public void doOutput() throws AutomatonException {
	}
	
}
