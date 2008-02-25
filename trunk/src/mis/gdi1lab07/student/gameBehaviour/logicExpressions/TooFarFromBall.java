package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;;

public class TooFarFromBall<T extends GameEnv> extends BaseLogicExpression<T> {

	/**
	 * Prüft ob die Entfernung zum Ball zu groß ist (Aufg. 5.1 Hinweis 2.4)
	 */
	public TooFarFromBall(T env) {
		super(env);
	}

	//TODO Ballentfernung evtl ändern
	@Override
	public boolean eval(T env) throws LogExpException {
		//System.out.print("Too Far from Ball? ");
		if (env.getBall()!=null){
			if (env.getBall().getDistance()>20){
				System.out.println("To far from ball"+true);
				return true;
			}
			else{
				//System.out.println(false);
				return false;
			}
			}
		System.out.println(false+" can't see it"); 
			return true;
	
	
	
	}
	
}