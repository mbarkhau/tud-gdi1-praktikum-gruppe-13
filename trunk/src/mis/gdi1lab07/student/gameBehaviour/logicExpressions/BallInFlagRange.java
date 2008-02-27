package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class BallInFlagRange<T extends GameEnv> extends
		BaseLogicExpression<T> {

	private final double range;
	
	private final int flagId;
	
	/**
	 * @param range within which logex wil trigger
	 */
	public BallInFlagRange(T env, double range, int flagId) {
		super(env);
		this.range = range;
		this.flagId = flagId;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector f = env.getFlag(flagId);
		FieldVector b = env.getBall();
		if (f != null && b != null){
			double distBallFlag = Utils.getVectorDistance(f, b);
			if (distBallFlag < range)
				return true;
		}
		return false;
	}

}
