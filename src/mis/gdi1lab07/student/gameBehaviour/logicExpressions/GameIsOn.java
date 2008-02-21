package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.GameEnv;
import atan2.model.ControllerAdaptor;

public class GameIsOn<T extends GameEnv> extends BaseLogicExpression<T> {
	
	public GameIsOn(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		return env.getPlayMode() == ControllerAdaptor.PLAY_MODE_PLAY_ON;
	}

}
