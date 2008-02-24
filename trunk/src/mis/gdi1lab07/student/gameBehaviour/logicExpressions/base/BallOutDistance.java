package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/**
 * Triggers if the ball is out of the specified distance.
 * 
 * @deprecated Wenn es keine allzu großen umstände macht, sollte man lieber
 *             BallInDistance in einer NotExpression stecken, sonst wuchern hier
 *             die klassen zu sehr aus.
 */
public class BallOutDistance<T extends GameEnv> extends BaseLogicExpression<T> {

	private double dist;

	/** Prüft ob die Entfernung zum Ball zu groß ist (Aufg. 5.1 Hinweis 2.4) */
	public BallOutDistance(T env) {
		// TODO Schussentfernung evtl ändern
		super(env);
		this.dist = 50;
	}

	public BallOutDistance(T env, double distance) {
		super(env);
		this.dist = distance;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector b = env.getBall();
		return (b != null) && (b.getDistance() > dist);
	}

}