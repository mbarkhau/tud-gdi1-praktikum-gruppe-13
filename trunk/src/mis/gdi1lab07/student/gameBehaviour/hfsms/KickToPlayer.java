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
		if (p != null){
			player.kick(Utils.convertDistToPow(p.getDist()), 
				p.getDir());
			env.setHfsmParam(PASSER_HAS_PASSED, new Boolean(true));
			System.out.println(env.getTick() + " kicked to " + passeeId + " at " + p);
		} else {
			player.turn(90);
			System.out.println(env.getTick() + " kick failed " + passeeId + ", no player");			
		}
	}
	
}
