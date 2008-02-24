package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

public class HasHeardRequest<T extends GameEnv> extends BaseLogicExpression<T> implements GameMessages{
	//Der Spieler wird aufgefordert, einen Pass anzunehmen.
	
	public HasHeardRequest(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		if(env.receivedMessage(REQUEST_ACCEPTION)) {
			env.removeMessage(REQUEST_ACCEPTION);
			return true;
		}
		else
			return false;
	}

}
