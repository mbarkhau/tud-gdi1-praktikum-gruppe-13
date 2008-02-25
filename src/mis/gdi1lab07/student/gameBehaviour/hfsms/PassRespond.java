package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameMessages;

/** Passpiel Annahme bestätigen. */
public class PassRespond<T> extends StudentHFSM<T> {
	
	private final FieldPlayer player;
	
	public PassRespond(FieldPlayer player){
		super();
		this.player = player;
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		player.say(GameMessages.ACCEPT_PASS);
	}
}
