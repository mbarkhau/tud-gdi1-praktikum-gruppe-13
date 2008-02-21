package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** Do the pass. */
public class Pass<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public Pass(FieldPlayer player) {
		super();
		this.player = player;
	}

}
