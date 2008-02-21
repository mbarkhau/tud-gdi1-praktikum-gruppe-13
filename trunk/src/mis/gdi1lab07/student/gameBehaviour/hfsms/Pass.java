package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameMessages;

/** Do the pass. */
public class Pass<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public Pass(FieldPlayer player) {
		super();
		this.player = player;
	}

	@Override
	public void doOutput() throws AutomatonException {
		int playerId = player.getEnv().findSpeaker(GameMessages.ACCEPT_PASS);
		if (playerId != -1)
			player.kick(100, player.getEnv().getOwnPlayer(playerId)
					.getDirection());
	}
}
