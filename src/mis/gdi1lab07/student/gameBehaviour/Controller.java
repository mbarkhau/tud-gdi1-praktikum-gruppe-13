package mis.gdi1lab07.student.gameBehaviour;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldPosition;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;
import atan2.model.ControllerAdaptor;
import atan2.model.Player;

/**
 * Central class which delegates callbacks, so FieldData is updated and requests
 * actions from implemented strategys.
 */
public class Controller extends ControllerAdaptor {

	private StudentHFSM<GameEnv> hfsm;

	private GameEnv env = new GameEnv();

	public Controller(FieldPlayer<GameEnv> p, StudentHFSM<GameEnv> hfsm) {
		this.hfsm = hfsm;
		this.env = p.getEnv();
	}

	// Callbacks to Controller

	@Override
	public void preInfo() {

	}

	@Override
	public void postInfo() {
		try {
			hfsm.input(env);
		} catch (AutomatonException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void infoHear(double dir, String msg) {
		env.addMsg(dir, msg);
	}

	@Override
	public void infoHearPlayMode(int playMode) {
		env.setPlayMode(playMode);
	}

	@Override
	public void infoHearReferee(int refMsg) {

	}

	@Override
	public void infoSeeBall(double dist, double dir) {
		env.setBall(new FieldVector(dist, dir));
	}

	@Override
	public void infoSeeFlagCenter(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_CENTER)
			flagId = FlagConstants.C;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.C_O_R;

		if (id == FLAG_LEFT)
			flagId = FlagConstants.C_O_L;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagCornerOther(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.T_O_R;

		if (id == FLAG_LEFT)
			flagId = FlagConstants.T_O_L;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagCornerOwn(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.O_O_R;

		if (id == FLAG_LEFT)
			flagId = FlagConstants.O_O_L;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagGoalOther(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_CENTER)
			flagId = FlagConstants.T_G_C;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.T_G_R;

		if (id == FLAG_LEFT)
			flagId = FlagConstants.T_G_L;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagGoalOwn(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_CENTER)
			flagId = FlagConstants.O_G_C;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.O_G_R;

		if (id == FLAG_LEFT)
			flagId = FlagConstants.O_G_L;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagLeft(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_OWN_50)
			flagId = FlagConstants.O_O_L_50;

		if (id == FLAG_OWN_40)
			flagId = FlagConstants.O_O_L_40;

		if (id == FLAG_OWN_30)
			flagId = FlagConstants.O_O_L_30;

		if (id == FLAG_OWN_20)
			flagId = FlagConstants.O_O_L_20;

		if (id == FLAG_OWN_10)
			flagId = FlagConstants.O_O_L_10;

		if (id == FLAG_OTHER_10)
			flagId = FlagConstants.T_O_L_10;

		if (id == FLAG_OTHER_20)
			flagId = FlagConstants.T_O_L_20;

		if (id == FLAG_OTHER_30)
			flagId = FlagConstants.T_O_L_30;

		if (id == FLAG_OTHER_40)
			flagId = FlagConstants.T_O_L_40;

		if (id == FLAG_OTHER_50)
			flagId = FlagConstants.T_O_L_50;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagOther(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_LEFT_10)
			flagId = FlagConstants.T_B_L_10;

		if (id == FLAG_LEFT_20)
			flagId = FlagConstants.T_B_L_20;

		if (id == FLAG_LEFT_30)
			flagId = FlagConstants.T_B_L_30;

		if (id == FLAG_RIGHT_10)
			flagId = FlagConstants.T_B_R_10;

		if (id == FLAG_RIGHT_20)
			flagId = FlagConstants.T_B_R_20;

		if (id == FLAG_RIGHT_30)
			flagId = FlagConstants.T_B_R_30;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagOwn(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_LEFT_10)
			flagId = FlagConstants.O_B_L_10;

		if (id == FLAG_LEFT_20)
			flagId = FlagConstants.O_B_L_20;

		if (id == FLAG_LEFT_30)
			flagId = FlagConstants.O_B_L_30;

		if (id == FLAG_RIGHT_10)
			flagId = FlagConstants.O_B_R_10;

		if (id == FLAG_RIGHT_20)
			flagId = FlagConstants.O_B_R_20;

		if (id == FLAG_RIGHT_30)
			flagId = FlagConstants.O_B_R_30;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagPenaltyOther(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_CENTER)
			flagId = FlagConstants.O_P_C;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.O_P_R;

		if (id == FLAG_LEFT)
			flagId = FlagConstants.O_P_L;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagPenaltyOwn(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_CENTER)
			flagId = FlagConstants.T_P_C;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.T_P_R;

		if (id == FLAG_LEFT)
			flagId = FlagConstants.T_P_L;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagRight(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_OWN_50)
			flagId = FlagConstants.O_O_R_50;

		if (id == FLAG_OWN_40)
			flagId = FlagConstants.O_O_R_40;

		if (id == FLAG_OWN_30)
			flagId = FlagConstants.O_O_R_30;

		if (id == FLAG_OWN_20)
			flagId = FlagConstants.O_O_R_20;

		if (id == FLAG_OWN_10)
			flagId = FlagConstants.O_O_R_10;

		if (id == FLAG_OTHER_10)
			flagId = FlagConstants.T_O_R_10;

		if (id == FLAG_OTHER_20)
			flagId = FlagConstants.T_O_R_20;

		if (id == FLAG_OTHER_30)
			flagId = FlagConstants.T_O_R_30;

		if (id == FLAG_OTHER_40)
			flagId = FlagConstants.T_O_R_40;

		if (id == FLAG_OTHER_50)
			flagId = FlagConstants.T_O_R_50;

		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeLine(int id, double dist, double dir) {

	}

	@Override
	public void infoSeePlayerOther(int id, double dist, double dir) {
		env.setOtherPlayer(id, dist, dir);
	}

	@Override
	public void infoSeePlayerOwn(int id, double dist, double dir) {
		env.setOwnPlayer(id, dist, dir);
	}
}
