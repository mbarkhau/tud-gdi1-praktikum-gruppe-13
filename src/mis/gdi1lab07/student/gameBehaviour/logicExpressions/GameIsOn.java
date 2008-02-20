package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import atan2.model.ControllerAdaptor;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GameIsOn<T extends GameEnv> implements LogicExpression<T> {

	private final T env;
	
	public GameIsOn(T env) {
		this.env = env;
	}
	
	@Override
	public boolean eval(T env) throws LogExpException {
		return env.getPlayMode() == ControllerAdaptor.PLAY_MODE_PLAY_ON;
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		if (eval(env))
			handler.constTrue();
		else
			handler.constFalse();
	}

}
