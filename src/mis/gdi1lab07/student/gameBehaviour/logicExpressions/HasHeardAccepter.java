package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

public class HasHeardAccepter<T extends GameEnv> extends BaseLogicExpression<T> implements GameMessages {

	//Passender Spieler hat Antwort von annehmendem Spieler erhalten

	public HasHeardAccepter(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		return (env.findSpeaker(ACCEPT_PASS) != -1);
	}
}
