package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;

public class WalkToBall<T> extends StudentHFSM<T> {
	
	private final FieldPlayer player;
	
	public WalkToBall(FieldPlayer player){
		this.player = player;
		this.setName(getClass().getName());
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		FieldVector ball = player.getEnv().getBall();
		if (ball.getDirection() != 0){
			player.turn(ball.getDirection());
		}else{
			player.dash(80);
		}
	}
}
