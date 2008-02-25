package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/**
 * Lässt player in Richtung gegnerisches Tor dribbeln / bzw. leicht nach vorne schiessen
 */
public class DribbleOnGoal<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public DribbleOnGoal(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	//TODO Player soll aufs Tor dribblen, d.h. den Ball vorlegen
	@Override
	public void doOutput() throws AutomatonException{
		System.out.println(player.getName()+" dribbles on goal. (kick 15)");
		this.player.kick(15, 0);
	}
	
}