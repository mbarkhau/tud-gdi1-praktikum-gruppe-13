package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;

public abstract class BaseLogicExpression<T extends GameEnv> implements LogicExpression<T>, FlagConstants{

	protected final T env;
	
	public BaseLogicExpression(T env) {
		this.env = env;
	}
	
	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		handler.variableReference(getClass().getName());
	}

}
