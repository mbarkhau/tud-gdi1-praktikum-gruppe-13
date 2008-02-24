package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

public class FindPassee<T extends GameEnv> extends StudentHFSM<T> {

	private final FieldPlayer<T> player;
	
	public FindPassee(FieldPlayer<T> player) {
		super();
		this.player = player;
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		for (FieldVector v : player.getEnv().getOwnPlayers()) {
			if (true) { //TODO: pick a random player
				player.turn(v.getDirection());
				return;
			}
		} 
	}
}
