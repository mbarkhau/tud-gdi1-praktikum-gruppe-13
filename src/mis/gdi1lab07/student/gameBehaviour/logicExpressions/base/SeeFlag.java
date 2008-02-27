package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/** Trigger, if the ball is in the FOV (players field of view) */
public class SeeFlag<T extends GameEnv> extends BaseLogicExpression<T> {

	int flagID;
	
	public SeeFlag(T env, int flagID) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector f = env.getFlag(flagID); 
		return f != null;
	}

}