package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensivePlayers;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/** N‰hert sich dem Ball an, da die Entfernung zu groﬂ ist (siehe Aufg. 5.1, Hinweis 2.4)
 */
public class DrawNearBall<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public DrawNearBall(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
		
		
	}
	
	//TODO Player soll sich dem Ball n‰hern bis die Entfernung klein genug ist.

}