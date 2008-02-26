package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

/**
 * @deprecated Wenn es keine allzu großen umstände macht, sollte man lieber
 *             BallInDistance benutzen, sonst wuchern hier die klassen zu sehr
 *             aus.
 */
public class BallLessThan30MetersAway<T extends GameEnv> extends
		BaseLogicExpression<T> implements LogicExpression<T> {

	public BallLessThan30MetersAway(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		if (env.getBall() == null)
			return false;
		return env.getBall().getDist() <= 30;
	}

}
