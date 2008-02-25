package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

public class HeardResponse<T extends GameEnv> extends BaseLogicExpression<T> implements GameMessages {

	//Passender Spieler hat Antwort von annehmendem Spieler erhalten

	public HeardResponse(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		return (env.findSpeaker(ACCEPT_PASS) != -1);
	}
	
	/** @return the id of a player who has issued a response */
	public int getPlayerId(){
		return -1;
	}
}
