package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class HasScouted<T extends GameEnv> implements LogicExpression<T> {

	private int turns = 0;
	
	@Override
	public boolean eval(T env) throws LogExpException {
		turns ++;
		boolean result = turns > 4;
		if (result)
			reset();
		return result;
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		if (eval(null))
			handler.constTrue();
		else
			handler.constFalse();
	}
	
	public void reset(){
		turns = 0;
	}

}
