package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/** @deprecated */
public class WalkToBall<T extends GameEnv> extends StudentHFSM<T> {
	
	private final FieldPlayer<T> player;
	
	public WalkToBall(FieldPlayer<T> player){
		super();
		this.player = player;
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		FieldVector ball = player.getEnv().getBall();
		if (ball.getDirection() != 0){
			player.turn(ball.getDirection());
		}else{
			player.dash(65);
		}
	}
}
