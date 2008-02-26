package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GrabBall<T extends GameEnv> extends BaseHfsm<T> {

	
	public GrabBall(FieldPlayer<T> player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doOutput() throws AutomatonException {
		// TODO Auto-generated method stub
		this.player.catchBall(this.player.getEnv().getBall().getDir());

	}

}
