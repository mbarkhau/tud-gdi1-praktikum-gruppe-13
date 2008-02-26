package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/**
 * Trigger when the player is in shooting range of the goal.
 * 
 * @deprecated Wenn es keine allzu großen umstände macht, sollte man lieber
 *             FlagInDistance benutzen, sonst wuchern hier die klassen zu sehr
 *             aus.
 */
public class InShootDistance<T extends GameEnv> extends
		BaseLogicExpression<T> {
	
	// TODO Schussentfernung evtl �ndern
	private double dist = 25;
	
	public InShootDistance(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector f = env.getFlag(T_G_C); 
		return (f != null) && f.getDist() < dist;
	}
}