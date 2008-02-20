package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class IsClosestToBall<T extends GameEnv> implements LogicExpression<T> {

	@Override
	public boolean eval(T env) throws LogExpException {
		double ownDistance = env.getBall().getDistance();
		for (FieldVector v : env.getOwnPlayers()) {
			if (Utils.getVectorDistance(env.getBall(), v) < ownDistance)
				return false;
		}
		return true;
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		// TODO Auto-generated method stub

	}

}
