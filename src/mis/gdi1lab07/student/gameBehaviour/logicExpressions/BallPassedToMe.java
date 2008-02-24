package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

public class BallPassedToMe<T extends GameEnv> extends BaseLogicExpression<T> implements GameMessages {

	//Stellt fest, dass ein Pass kommt, da ANNOUNCE_PASS empfangen wurde.
	public BallPassedToMe(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		if(env.receivedMessage(ANNOUNCE_PASS)) {
			env.removeMessage(ANNOUNCE_PASS);
			return true;
		}
		else
			return false;
	}

}
