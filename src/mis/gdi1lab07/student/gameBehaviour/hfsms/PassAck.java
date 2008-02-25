package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

public class PassAck<T extends GameEnv> extends BaseHfsm<T> {

	public PassAck(FieldPlayer<T> player) {
		super(player);
	}

	@Override
	public void doOutput() throws AutomatonException {
		player.say(GameMessages.PASS_ACK + env.getPlayerId());
	}
}
