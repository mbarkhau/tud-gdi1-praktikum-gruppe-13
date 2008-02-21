package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensivePlayers;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** 
 * Läuft ohne Ball aufs Tor zu, da ein anderer Spieler näher am Tor ist 
 * (Aufgabe 5.1.2 
 */
public class RunAhead<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public RunAhead(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	// TODO player soll ohne Ball in Richtung gegnerisches Tor laufen
	

}
