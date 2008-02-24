package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

/** @deprecated */
public class FollowBall<T extends GameEnv> extends BaseHfsm<T> {

	// der große delta, ist damit der spieler nicht blöd den ball anschaut,
	// wenn er vor ihm herrollt
	private double delta = 10;
	
	public FollowBall(FieldPlayer<T> player) {
		super(player);
	}

	public void doOutput() throws AutomatonException {
		FieldVector b = player.getEnv().getBall();
		if (b == null)
			player.turn(90);
		else if (!Utils.inDelta(b.getDirection(), 0, delta))
			player.turn(b.getDirection());
		else
			player.dash(60);
	}

}
