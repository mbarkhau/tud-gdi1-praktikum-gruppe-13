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
		int mode = env.getPlayMode();
		return mode != ControllerAdaptor.PLAY_MODE_BEFORE_KICK_OFF
				&& mode != ControllerAdaptor.PLAY_MODE_GOAL_OWN
				&& mode != ControllerAdaptor.PLAY_MODE_GOAL_OTHER
				&& mode != ControllerAdaptor.PLAY_MODE_TIME_OVER;
	}

}
