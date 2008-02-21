package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class IsInShootDistance<T extends GameEnv> extends BaseLogicExpression<T> {

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
		if (env.getFlag(50)!= null){
			if (env.getFlag(50).getAge()==0){
				return (env.getFlag(50).getDistance()<25);
			}
		}
		return false;
	}
}