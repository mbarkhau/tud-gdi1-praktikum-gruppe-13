package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.Utils;

/** 
 * Zwischenzustand der sofort feuert,
 * wenn Spiel läuft.
 * Hat keine Funktionen.
 * 
 */
public class LookAhead<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public LookAhead(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		if(Utils.debugThis(Utils.DBG_ALL))
		System.out.println(player.getNumber()+" looks ahead.");
	}

}
