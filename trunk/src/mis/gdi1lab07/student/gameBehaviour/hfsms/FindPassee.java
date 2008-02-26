package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

public class FindPassee<T extends GameEnv> extends BaseHfsm<T> {

	public FindPassee(FieldPlayer<T> player) {
		super(player);
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		for (FieldVector v : env.getOwnPlayers()) {
			if (true) {
				player.turn(v.getDir());
				return;
			}
		}
		player.turn(90);
	}
}
