package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GotoBall<T extends GameEnv> extends BaseHfsm<T> {

	private int power = POWER_RUN;
	
	public GotoBall(FieldPlayer<T> player) {
		super(player);
	}

	@Override
	public void doOutput() throws AutomatonException {
		FieldVector b = env.getBall();
		gotoVector(b, power, DELTA_DYNAMIC);
	}
	
	/**
	 * Set the power with which dashes should be made <br>
	 * default: {@link BaseHfsm#POWER_RUN}
	 */
	public void setPower(int power) {
		this.power = power;
	}
	
	
}
