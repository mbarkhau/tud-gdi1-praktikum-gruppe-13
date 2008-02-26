package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/** Trigger, if the ball is in the FOV (players field of view) */
public class BallVisible<T extends GameEnv> extends BaseLogicExpression<T> {

	public BallVisible(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector b = env.getBall() ; 
		return (b != null) && Math.abs(b.getDir()) < 45;
	}

}
