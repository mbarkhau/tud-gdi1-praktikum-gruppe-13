package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

import java.util.Collection;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class LookingAtPlayer<T extends GameEnv> extends BaseLogicExpression<T> {

	private int playerId;

	private boolean ownTeam;

	public LookingAtPlayer(T env, boolean ownTeam, int playerId) {
		super(env);
		this.ownTeam = ownTeam;
		this.playerId = playerId;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		FieldVector p = (ownTeam) ? env.getOwnPlayer(playerId) : env
				.getOtherPlayer(playerId);

		if (p == null) {
			// irgend ein spieler ist im sichtfeld
			Collection<FieldVector> players = (ownTeam) ? env.getOwnPlayers()
					: env.getOtherPlayers();
			for (FieldVector current : players){
				if (Utils.inDelta(current.getDir(), 0, 25))
					return true;
			}	
		}
		
		return (p != null) && Utils.inDelta(p.getDir(), 0, 25);
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
