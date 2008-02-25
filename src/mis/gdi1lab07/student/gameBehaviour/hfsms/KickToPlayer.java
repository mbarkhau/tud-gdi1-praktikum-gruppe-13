package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;
import mis.gdi1lab07.student.gameData.Utils;

/** Do the pass. */
public class KickToPlayer<T extends GameEnv> extends BaseHfsm<T> {

	public KickToPlayer(FieldPlayer<T> player) {
		super(player);
	}

	@Override
	public void doOutput() throws AutomatonException {
		Integer passeeId = (Integer)env.getHfsmParam(PASSER_RESPONSE_PLAYER_ID);
		if (passeeId != null)
			passeeId = env.findSpeaker(GameMessages.PASS_RESPONSE);
			
		FieldVector p = env.getOwnPlayer(passeeId);
		if (p != null)
			player.kick(Utils.convertDistToPow(p.getDistance()), 
				p.getDirection());
	}
	
}
