package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class IsInShootDistance<T extends GameEnv> extends BaseLogicExpression<T> implements FlagConstants{

	/**
	 * Überprüft ob sich der Player in Schuss-Distanz befindet
	 * @param env
	 */
	public IsInShootDistance(T env) {
		super(env);
	}

	//TODO Schussentfernung evtl ändern
	@Override
	public boolean eval(T env) throws LogExpException {
		//System.out.print("IsInShootDistance? ");
		if (env.getFlag(T_G_C)!= null){
			//System.out.println("Dist To GoalC: "+env.getFlag(T_G_C).getDistance());
			if(env.getFlag(T_G_C).getDistance()<=25.0){
				//System.out.println(true);
				return true;
			}
					
			}
		//System.out.println(false);
		return false;
	}
}