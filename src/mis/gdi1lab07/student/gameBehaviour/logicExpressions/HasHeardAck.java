package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

/** Trigger if a passer has issued an ack for pass */
public class HasHeardAck<T extends GameEnv> extends BaseLogicExpression<T> implements GameMessages {

	//Passender Spieler hat Antwort von annehmendem Spieler erhalten

	public HasHeardAck(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		return env.receivedMessage(PASS_ACK);
	}
}
