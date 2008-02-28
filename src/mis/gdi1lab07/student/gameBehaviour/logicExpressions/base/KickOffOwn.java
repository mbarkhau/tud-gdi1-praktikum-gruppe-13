package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.GameEnv;
import atan2.model.ControllerAdaptor;

public class KickOffOwn<T extends GameEnv> extends BaseLogicExpression<T>{

	public KickOffOwn(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		int mode = env.getPlayMode();
		return mode == ControllerAdaptor.PLAY_MODE_GOAL_KICK_OWN;
	}

}
