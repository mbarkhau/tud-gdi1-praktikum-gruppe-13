package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.GameEnv;
import atan2.model.ControllerAdaptor;

public class PlayOn<T extends GameEnv> extends BaseLogicExpression<T>{

	public PlayOn(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		int mode = env.getPlayMode();
		return mode == ControllerAdaptor.PLAY_MODE_PLAY_ON || 
				mode == ControllerAdaptor.PLAY_MODE_KICK_OFF_OWN ||
				mode == ControllerAdaptor.PLAY_MODE_CORNER_KICK_OWN ||
				mode == ControllerAdaptor.PLAY_MODE_FREE_KICK_OWN ||
				mode ==	ControllerAdaptor.PLAY_MODE_GOAL_KICK_OWN ||
				mode == ControllerAdaptor.PLAY_MODE_KICK_IN_OWN;
	}

}
