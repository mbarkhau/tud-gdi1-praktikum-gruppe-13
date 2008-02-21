package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.GameEnv;

public class HasScouted<T extends GameEnv> extends BaseLogicExpression<T> {

	public HasScouted(T env) {
		super(env);
	}

	private int turns = 0;
	
	@Override
	public boolean eval(T env) throws LogExpException {
		turns ++;
		boolean result = turns > 4;
		if (result)
			reset();
		return result;
	}
	
	public void reset(){
		turns = 0;
	}

}
