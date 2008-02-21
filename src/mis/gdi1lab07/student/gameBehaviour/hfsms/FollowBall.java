package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;

public class FollowBall<T> extends StudentHFSM<T> {


	private FieldPlayer player;
	
	public FollowBall(FieldPlayer player) {
		super();
		this.player = player;
	}
	
	private void turnToBall() {
		player.turn(player.getEnv().getBall().getDirection());
	}
	
	public void doOutput() throws AutomatonException {
		turnToBall();
		player.dash(50);
	}
	
}
