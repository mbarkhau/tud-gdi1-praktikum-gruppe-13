package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class IsAtBall<T extends GameEnv> implements LogicExpression<T> {
	
	private T env;
	
	public IsAtBall(T env) {
		this.env = env;
	}
	
	@Override
	public boolean eval(T env) throws LogExpException {
		return env.getBall().getDistance() < 0.5;
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		if (eval(env))
			handler.constTrue();
		else
			handler.constFalse();
	}

}
