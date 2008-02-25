package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

/** Passpiel Annahme bestätigen. */
public class PassRespond<T extends GameEnv> extends BaseHfsm<T> {
		
	public PassRespond(FieldPlayer<T> player){
		super(player);
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		player.say(GameMessages.PASS_RESPONSE + env.getPlayerId());
	}
}
