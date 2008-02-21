package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.GameEnv;

public class HasHeardRequest<T extends GameEnv> extends BaseLogicExpression<T> {

	public HasHeardRequest(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		// TODO Auto-generated method stub
		return false;
	}

}
