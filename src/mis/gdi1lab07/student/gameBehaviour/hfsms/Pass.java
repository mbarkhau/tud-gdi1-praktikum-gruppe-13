package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameMessages;
import mis.gdi1lab07.student.gameData.Utils;

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
		if (playerId != -1){
			FieldVector passee = player.getEnv().getOwnPlayer(playerId);
			player.kick(Utils.convertDistToPow(passee.getDistance()), 
					passee.getDirection());
		}
	}
}
