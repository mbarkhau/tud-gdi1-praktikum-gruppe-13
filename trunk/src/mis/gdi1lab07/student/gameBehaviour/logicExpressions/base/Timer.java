package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.GameEnv;

/** Triggers every triggercount calls*/
public class Timer<T extends GameEnv> extends BaseLogicExpression<T> {

	private int timer = 0;
	
	private int triggerCount= 0;
	
	
	public Timer(T env, int triggerCount) {
		super(env);
		this.triggerCount = triggerCount;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		timer++;
		if (timer > triggerCount)
			timer = 0;
		
		return timer == 0;
	}

}
