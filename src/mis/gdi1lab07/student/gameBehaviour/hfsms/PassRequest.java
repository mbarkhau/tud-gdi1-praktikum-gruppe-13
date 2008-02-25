package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

/** Passpiel Annahme anfordern. */
public class PassRequest<T extends GameEnv> extends BaseHfsm<T> {
		
	public PassRequest(FieldPlayer<T> player){
		super(player);
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		player.say(PASS_REQUEST + env.getPlayerId());
	}
}
