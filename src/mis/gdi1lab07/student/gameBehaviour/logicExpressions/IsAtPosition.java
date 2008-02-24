package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/**
 *  @deprecated Wenn es keine allzu großen umstände macht, sollte man lieber
 *             FlagInDistance benutzen, sonst wuchern hier
 *             die klassen zu sehr aus.
 */
public class IsAtPosition<T extends GameEnv> extends BaseLogicExpression<T> implements
		LogicExpression<T> {
	
	public IsAtPosition(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector f = env.getFlag(env.getPlayerPosition());
		return f != null && f.getDistance() < 8;
	}

}
