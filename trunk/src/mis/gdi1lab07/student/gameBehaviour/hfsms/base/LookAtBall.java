package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

public class LookAtBall<T extends GameEnv> extends BaseHfsm<T> {

	public LookAtBall(FieldPlayer<T> player) {
		super(player);
	}

	@Override
	public void doOutput() throws AutomatonException {
		FieldVector b = this.player.getEnv().getBall();
		if (b != null)
			this.player.turn(b.getDirection());
		else 
			this.player.turn(90);
	}

}
