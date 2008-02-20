package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class HasHeardAccepter<T extends GameEnv> implements LogicExpression<T> {

	T env;
	
	public HasHeardAccepter(T env) {
		this.env = env;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		return false;
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		// TODO Auto-generated method stub

	}

}
