package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GotoFlag<T extends GameEnv> extends BaseHfsm<T> {

	private int flagId;

	private int power = POWER_RUN;

	public GotoFlag(FieldPlayer<T> player, int flagId) {
		super(player);
		this.flagId = flagId;
	}

	@Override
	public void doOutput() throws AutomatonException {
		FieldVector f = env.getFlag(flagId);
		gotoVector(f, power, DELTA_STATIC);
	}

	/**
	 * Set the power with which dashes should be made <br>
	 * default: {@link BaseHfsm#POWER_RUN}
	 */
	public void setPower(int power) {
		this.power = power;
	}

}
