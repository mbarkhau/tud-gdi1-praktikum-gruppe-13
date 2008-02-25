package mis.gdi1lab07.student.gameBehaviour.logicExpressions.base;

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

		return (p != null) && Utils.inDelta(p.getDirection(), 0, 25);
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
