package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/** Trigger if the a certain flag is in the specified distance. */
public class FlagInDistance<T extends GameEnv> extends BaseLogicExpression<T> {

	private double dist;
	
	private int flagId = INVALD;

	public FlagInDistance(T env, int flagId, double distance) {
		super(env);
		this.flagId = flagId;
		this.dist = distance;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector f = env.getFlag(flagId); 
		return (f != null) && f.getDist() < dist;
	}

}
