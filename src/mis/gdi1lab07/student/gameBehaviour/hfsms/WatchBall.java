package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class WatchBall<T extends GameEnv> extends StudentHFSM<T> {

	private final FieldPlayer<T> player;

	public WatchBall(FieldPlayer<T> player) {
		super();
		this.player = player;
	}

	@Override
	public void doOutput() throws AutomatonException {
		GameEnv env = player.getEnv();
		if (env.getBall() != null)
			player.turn(env.getBall().getDirection());
		else
			player.turn(72);
	}

}
