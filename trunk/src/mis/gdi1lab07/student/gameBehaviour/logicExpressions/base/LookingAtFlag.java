package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

/**
 * Trigger if the player is looking more or less in the direction of the
 * specified Flag.
 */
public class LookingAtFlag<T extends GameEnv> extends BaseLogicExpression<T> {
	
	// TODO Winkel evtl noch zu �ndern
	private double DELTA = 0.5;
	
	private int flagId;

	public LookingAtFlag(T env, int flagId) {
		super(env);
		this.flagId = flagId;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector f = env.getFlag(flagId);
		return (f != null) && Utils.inDelta(f.getDirection(), 0, DELTA);
	}

}
