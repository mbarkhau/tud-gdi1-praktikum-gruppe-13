package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class GoToStartPosition<T extends GameEnv> extends BaseHfsm<T> {

	private int power = POWER_WALK;
	
	public GoToStartPosition(FieldPlayer<T> player) {
		super(player);
	}

	/**
	 * If the player can use the move command, she moves randomly otherwise dash
	 * to the flag.
	 */
	public void doOutput() throws AutomatonException {
		if (Utils.canUseMove(env.getPlayMode())) {
			player.move(0, 0);
		} else {
			FieldVector f = env.getFlag(Utils.getPlayerPos(player
					.getNumber()));
			gotoVector(f, power, DELTA_DYNAMIC);
		}
	}
	
	/**
	 * Set the power with which dashes should be made <br>
	 * default: {@link BaseHfsm#POWER_WALK}
	 */
	public void setPower(int power) {
		this.power = power;
	}
}
