package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class BallPassedByMe<T extends GameEnv> extends BaseLogicExpression<T>
		implements LogicExpression<T> {
	
	//Passender Spieer hat Ball erfolgreich gepasst, wenn Entfernung von 
	//Spieler zu Ball größer als 3 Meter ist.

	public BallPassedByMe(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		return env.getBall().getDistance() > 3;
	}

}
