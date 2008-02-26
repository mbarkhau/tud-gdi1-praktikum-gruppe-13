package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class IsClosestToBall<T extends GameEnv> extends BaseLogicExpression<T> {

	public IsClosestToBall(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		if (env.getBall() == null)
			return false;
		double ownDistance = env.getBall().getDist();
		for (FieldVector v : env.getOwnPlayers()) {
			if (Utils.getVectorDistance(env.getBall(), v) < (ownDistance - 0.5))
				return false;
		}
		return true;
	}

}
