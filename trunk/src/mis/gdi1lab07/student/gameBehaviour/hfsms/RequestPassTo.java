package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

/** Passpiel Annahme anfordern. */
public class RequestPassTo<T extends GameEnv> extends StudentHFSM<T> {
	
	private final FieldPlayer<T> player;
	
	public RequestPassTo(FieldPlayer<T> player){
		super();
		this.player = player;
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		player.say(GameMessages.REQUEST_ACCEPTION);
	}
}
