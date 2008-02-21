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

	// TODO FLAGGEN ÄNDERN!!!
	@Override
	public void infoSeeFlagCenter(int id, double dist, double dir) {
		boolean isRight = player.isTeamEast();
		int flagId = FlagConstants.INVALD;
		
		if (id == FLAG_CENTER)
			flagId = FlagConstants.C;

		if (id == FLAG_RIGHT)
			flagId = (isRight) ? FlagConstants.C_T : FlagConstants.C_B ;
		
		if (id == FLAG_LEFT)
			flagId = (isRight) ? FlagConstants.C_B : FlagConstants.C_T ;
		
		env.setFlag(flagId, dist, dir);
	}

	// TODO FLAGGEN ÄNDERN!!!
	@Override
	public void infoSeeFlagCornerOther(int id, double dist, double dir) {
		boolean isRight = player.isTeamEast();
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_RIGHT)
			flagId = (isRight) ? FlagConstants.L_T : FlagConstants.R_T;
		
		if (id == FLAG_LEFT)
			flagId = (isRight) ? FlagConstants.L_B : FlagConstants.R_B;
		
		env.setFlag(flagId, dist, dir);
	}

	// TODO FLAGGEN ÄNDERN!!!
	@Override
	public void infoSeeFlagCornerOwn(int id, double dist, double dir) {
		boolean isRight = player.isTeamEast();
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_RIGHT)
			flagId = (isRight) ? FlagConstants.R_T : FlagConstants.L_T;
		
		if (id == FLAG_LEFT)
			flagId = (isRight) ? FlagConstants.R_B : FlagConstants.L_B;
		
		env.setFlag(flagId, dist, dir);
	}


	@Override
	public void infoSeeFlagGoalOther(int id, double dist, double dir) {
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_CENTER)
			flagId = FlagConstants.G_T_C;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.G_T_R;
		
		if (id == FLAG_LEFT)
			flagId = FlagConstants.G_T_L;
		
		env.setFlag(flagId, dist, dir);
	}
	
	@Override
	public void infoSeeFlagGoalOwn(int id, double dist, double dir) {
		boolean isRight = player.isTeamEast();
		int flagId = FlagConstants.INVALD;

		if (id == FLAG_CENTER)
			flagId = FlagConstants.G_O_C;

		if (id == FLAG_RIGHT)
			flagId = FlagConstants.G_O_R;
		
		if (id == FLAG_LEFT)
			flagId = FlagConstants.G_O_L;
		
		env.setFlag(flagId, dist, dir);
	}

	@Override
	public void infoSeeFlagLeft(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagOther(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagOwn(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagPenaltyOther(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagPenaltyOwn(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagRight(int id, double dist, double dir) {

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
