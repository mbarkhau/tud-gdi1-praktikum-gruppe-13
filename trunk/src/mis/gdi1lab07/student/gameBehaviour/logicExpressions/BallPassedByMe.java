package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class BallPassedByMe<T extends GameEnv> extends BaseLogicExpression<T>
		implements LogicExpression<T> {
	
	//Passender Spieer hat Ball erfolgreich gepasst, wenn Entfernung zunimmt
	
	private double distA = -999;
	
	private double distB = -999;
	
	public BallPassedByMe(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		if (env.getBall() == null)
			return false;
		if (distA < 0)
			distA = env.getBall().getDistance();
		else if (distB < 0)
			distB = env.getBall().getDistance();
		else if (distA < distB){
			distA = -999;
			distB = -999;
			System.out.println("passed by me");
			return true;
		}
		return false;
	}

}
