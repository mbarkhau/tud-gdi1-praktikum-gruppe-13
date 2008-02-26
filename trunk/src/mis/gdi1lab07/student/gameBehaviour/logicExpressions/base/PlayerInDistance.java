package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import java.util.Collection;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

/** Trigger if a player of the specified team is in a certain range range. */
public class PlayerInDistance<T extends GameEnv> extends BaseLogicExpression<T> {

	private boolean ownTeam;

	private double dist;

	public PlayerInDistance(T env, boolean ownTeam, double distance) {
		super(env);
		this.ownTeam = ownTeam;
		this.dist = distance;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		Collection<FieldVector> players = (ownTeam) ? env.getOwnPlayers() : env
				.getOtherPlayers();
		
		for (FieldVector v : players) {
			if (Utils.inDelta(v.getDir(), 0) && v.getDist() < dist)
				return true;
		}
		return false;
	}

}
