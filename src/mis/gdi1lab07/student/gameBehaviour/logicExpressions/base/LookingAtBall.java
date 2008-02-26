package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

/**
 * Trigger if the player is looking more or less in the direction of the ball.
 */
public class LookingAtBall<T extends GameEnv> extends BaseLogicExpression<T> {

	// TODO Winkel evtl noch zu �ndern
	private double DELTA = 0.5;
	
	public LookingAtBall(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector b = env.getBall();
		return (b != null) && Utils.inDelta(b.getDir(), 0, DELTA);
	}

}
