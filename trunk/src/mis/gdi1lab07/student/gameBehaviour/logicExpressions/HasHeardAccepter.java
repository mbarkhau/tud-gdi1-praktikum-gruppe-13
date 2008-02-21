package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

public class HasHeardAccepter<T extends GameEnv> extends BaseLogicExpression<T> implements GameMessages {

	//Passender Spieler hat Antwort von annehmendem Spieler erhalten

	public HasHeardAccepter(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		if(env.receivedMessage(ACCEPT_PASS)) {
			env.removeMessage(ACCEPT_PASS);
			return true;
		}
		else
			return false;
	}
}
