package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

/** N�hert sich dem Ball an, da die Entfernung zu gro� ist (siehe Aufg. 5.1, Hinweis 2.4)
 */
public class DrawNearBall<T extends GameEnv> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public DrawNearBall(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
		
		
	}
	
	//TODO Player soll sich dem Ball n�hern bis die Entfernung klein genug ist.

	@Override
	public void doOutput() throws AutomatonException {
		this.player.dash(80);
	}
}