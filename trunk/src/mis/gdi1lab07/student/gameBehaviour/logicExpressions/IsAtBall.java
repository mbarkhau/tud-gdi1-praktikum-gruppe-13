package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class IsAtBall<T extends GameEnv> extends BaseLogicExpression<T> {
	
	public IsAtBall(T env) {
		super(env);
	}
	
	@Override
	public boolean eval(T env) throws LogExpException {
		return env.getBall().getDistance() < 0.5;
	}

}
