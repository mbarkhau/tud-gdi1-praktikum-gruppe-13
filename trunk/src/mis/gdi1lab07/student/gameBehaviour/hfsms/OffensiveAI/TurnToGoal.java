package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

public class TurnToGoal<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public TurnToGoal(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}

}
