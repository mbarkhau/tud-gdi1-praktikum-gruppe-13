package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class WatchBall<T extends GameEnv> extends StudentHFSM<T> {

	private final FieldPlayer player;
	
	public WatchBall(FieldPlayer player) {
		super();
		this.player = player;
	}

	@Override
	public void doOutput() throws AutomatonException {
		if (player.getEnv().getBall() != null)
		player.turn(player.getEnv().getBall().getDirection());
	}
	
}
