package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class BallPassedByMe<T extends GameEnv> extends BaseLogicExpression<T>
		implements LogicExpression<T> {
	
	public BallPassedByMe(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		Boolean hasPassed = (Boolean)env.getHfsmParam(PASSER_HAS_PASSED);
		return (hasPassed != null) && hasPassed.booleanValue();
	}

}
