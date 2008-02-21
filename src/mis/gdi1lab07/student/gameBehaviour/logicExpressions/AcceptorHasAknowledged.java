package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

public class AcceptorHasAknowledged<T extends GameEnv> extends
		BaseLogicExpression<T> implements LogicExpression<T>, GameMessages {
	
	//Der den Pass akzeptierende Spieler hat die Ank�ndigung empfangen und gibt deshalb
	//keine Antwort mehr

	public AcceptorHasAknowledged(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		return (env.findSpeaker(ACCEPT_PASS) != -1);
	}

}
