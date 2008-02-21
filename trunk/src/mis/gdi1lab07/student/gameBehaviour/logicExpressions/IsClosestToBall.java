package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class IsClosestToBall<T extends GameEnv> extends BaseLogicExpression<T> {

	public IsClosestToBall(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		double ownDistance = env.getBall().getDistance();
		for (FieldVector v : env.getOwnPlayers()) {
			if (Utils.getVectorDistance(env.getBall(), v) < ownDistance)
				return false;
		}
		return true;
	}

}
