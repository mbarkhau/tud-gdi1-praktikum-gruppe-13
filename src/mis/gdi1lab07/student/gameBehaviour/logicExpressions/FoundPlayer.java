package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;
import atan2.model.ControllerAdaptor;

public class FoundPlayer<T extends GameEnv> extends BaseLogicExpression<T> {

	public FoundPlayer(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		for (FieldVector v : env.getOwnPlayers()) {
			if (Utils.isDirectionEqual(v.getDirection(), 0) && v.getDistance() < 100)
				return true;
		}
		return false;
	}

}
