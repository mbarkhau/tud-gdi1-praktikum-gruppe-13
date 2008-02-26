package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/** Trigger if ball is in the specified distance. */
public class BallInDistance<T extends GameEnv> extends BaseLogicExpression<T> implements
		LogicExpression<T> {

	private double dist;
	
	public BallInDistance (T env, double distance) {
		super(env);
		this.dist = distance;
	}
	
	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector ball = env.getBall(); 
		return (ball != null) && ball.getDist() < dist;
	}

}
