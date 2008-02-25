package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

/**
 * Lässt player aufs Tor schiessen
 */
public class Shoot<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public Shoot(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	public void doOutput() throws AutomatonException{
		System.out.println(this.player.getNumber()+" shoots (kick 100)");
		this.player.kick(100, 0);
	}

}