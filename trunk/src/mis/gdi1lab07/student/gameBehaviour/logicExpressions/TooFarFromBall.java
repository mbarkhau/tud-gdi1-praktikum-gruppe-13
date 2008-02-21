package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class TooFarFromBall<T extends GameEnv> extends BaseLogicExpression<T> {

	/**
	 * Pr�ft ob die Entfernung zum Ball zu gro� ist (Aufg. 5.1 Hinweis 2.4)
	 */
	public TooFarFromBall(T env) {
		super(env);
	}

	//TODO Schussentfernung evtl �ndern
	@Override
	public boolean eval(T env) throws LogExpException {
		if (env.getBall()!=null){
			return (env.getBall().getDistance()>50);
		}
		else return true;
	
	
	
	}
	
}