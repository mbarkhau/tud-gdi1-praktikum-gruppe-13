package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class PlayerInFlagRange<T extends GameEnv> extends BaseLogicExpression<T> {

	private int playerId;
	
	private final int flagId;
	
	private final double range;
	
	public PlayerInFlagRange(T env, int playerId, int flagId, double range) {
		super(env);
		this.playerId = playerId;
		this.flagId = flagId;
		this.range = range;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		// TODO unimplemented for specific players
		FieldVector f = env.getFlag(flagId);
		for (FieldVector p : env.getOtherPlayers()) {
			double distToFlag = Utils.getVectorDistance(f, p);
			if (distToFlag < range)
				return true;
		}
		return false;
	}
	
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
