package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

/** @deprecated */
public class GoToStartPosition<T extends GameEnv> extends StudentHFSM<T> {

	private FieldPlayer<T> player;

	public GoToStartPosition(FieldPlayer<T> player) {
		super();
		this.player = player;
	}

	/**
	 * If the player can use the move command, she moves randomly otherwise dash
	 * to the flag.
	 */
	public void doOutput() throws AutomatonException {
		GameEnv env = player.getEnv();
		if (Utils.canUseMove(env.getPlayMode())) {
			player.move(0, 0);
		} else {
			FieldVector flagVector = env.getFlag(Utils.getPlayerPos(player
					.getNumber()));
			if (flagVector != null) {
				if (!Utils.inDelta(flagVector.getDirection(), 0))
					player.turn(flagVector.getDirection());
				else
					player.dash(70);
			}
		}
	}
}
