package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** 
 * Läuft ohne Ball aufs Tor zu, da ein anderer Spieler näher am Tor ist 
 * (Aufgabe 5.1.2 
 */
public class RunOnGoal<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public RunOnGoal(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		this.player.dash(80);
	}
	

}
