package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

public class EnemyLessThan10MetersAway<T extends GameEnv> extends BaseLogicExpression<T> implements
		LogicExpression<T> {
	
	public EnemyLessThan10MetersAway(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		for (FieldVector current : env.getOtherPlayers()) {
			if(current.getDistance()<=10)
				return true;
		}
		return false;
	}

}
