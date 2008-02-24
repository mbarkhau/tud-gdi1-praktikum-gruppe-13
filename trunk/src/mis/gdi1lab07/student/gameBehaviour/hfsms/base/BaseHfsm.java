package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

/** Base class so constants and player is available*/
public abstract class BaseHfsm<T extends GameEnv> extends StudentHFSM<T> implements
		FlagConstants, GameMessages {

	protected final FieldPlayer<T> player;

	/** initializes with local player and the name of the class*/
	public BaseHfsm(FieldPlayer<T> player) {
		super();
		this.player = player;
		this.setName(getClass().getName());
	}

}
