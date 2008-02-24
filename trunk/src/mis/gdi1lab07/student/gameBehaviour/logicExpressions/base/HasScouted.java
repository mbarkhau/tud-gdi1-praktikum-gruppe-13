package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.GameEnv;

public class HasScouted<T extends GameEnv> extends BaseLogicExpression<T> {

	public HasScouted(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		return (env.getBall() != null) && (env.getFlag(T_G_C) != null)
				&& (env.getFlag(O_G_C) != null) && (env.getFlag(C_O_L) != null)
				&& (env.getFlag(C_O_R) != null);
	}

}
