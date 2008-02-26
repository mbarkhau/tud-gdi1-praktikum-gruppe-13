package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;
import mis.gdi1lab07.student.gameData.HfsmParamConstants;
import mis.gdi1lab07.student.gameData.Utils;

/**
 * Base class so constants and local variables are available. Intended as base
 * class for HFSMs on the lowest level. ie. actually issue commands to the
 * player.
 */
public abstract class BaseHfsm<T extends GameEnv> extends StudentHFSM<T>
		implements FlagConstants, GameMessages, HfsmParamConstants {

	public static int POWER_WALK = 65;

	public static int POWER_RUN = 80;

	public static int POWER_SPRINT = 100;

	/** Use for vectors which don't move */
	public static double DELTA_STATIC = 0.5;

	/** Use for vectors which can move */
	public static double DELTA_DYNAMIC = 10;

	protected final FieldPlayer<T> player;

	protected final T env;

	/** initializes with local player and the name of the class */
	public BaseHfsm(FieldPlayer<T> player) {
		super();
		this.player = player;
		this.env = player.getEnv();
		this.setName(getClass().getName());
	}

	@Override
	public void doOutput() throws AutomatonException {
		if (Utils.DEBUG_LEVEL == Utils.DBG_ALL)
			System.out.println(env.getTick() + " PlayerId "
					+ player.getNumber() + " State: "
					+ this.getCurrentState().toString());
	}

	public void gotoVector(FieldVector v) {
		gotoVector(v, POWER_RUN, DELTA_DYNAMIC);
	}

	public void gotoVector(FieldVector v, int power) {
		gotoVector(v, DELTA_DYNAMIC);
	}

	public void gotoVector(FieldVector v, double delta) {
		gotoVector(v, POWER_RUN);
	}

	/**
	 * Common logic to go to a vector, tries to align and dash, or turn so the
	 * vector might be available next time.
	 * 
	 * @param power
	 *            with which to dash
	 * @param delta
	 *            for which an angle is considered 0
	 */
	public void gotoVector(FieldVector v, int power, double delta) {
		if (v == null)
			player.turn(90);
		else if (!Utils.inDelta(v.getDir(), 0, delta))
			player.turn(v.getDir());
		else
			this.player.dash(power);
	}
}
