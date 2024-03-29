package mis.gdi1lab07.dribbling;

import atan2.model.ControllerAdaptor;

/**
 * This class must be implemented by the students to create a player with
 * dribbling capabilities.
 */
public class DribblePlayer extends ControllerAdaptor {

	private int playMode = PLAY_MODE_BEFORE_KICK_OFF;

	private static int WAIT = 0;

	private static int FOCUS = 1;

	private static int GOTOBALL = 2;

	private static int DRIBBLE = 3;

	private static int SHOOT = 4;

	private int playerObjective = WAIT;

	private static int BALL_LOST = 0;

	private static int BALL_FOUND = 1;

	private static int BALL_CLOSE = 2;

	private int ballState = BALL_LOST;

	private double ballDirection = 0;

	private double ballDistance = 1000;

	private double goalDirection = 0;

	private double goalLastSeen = 100;

	private double goalDistance = 1000;

	private boolean skipEval = false;

	@Override
	public void preInfo() {
		goalLastSeen++;
		if (PLAY_MODE_BEFORE_KICK_OFF == playMode && ballDistance > 4) {
			player.move(0, 0);
			playerObjective = WAIT;
		} else if (PLAY_MODE_KICK_IN_OTHER == playMode
				|| PLAY_MODE_CORNER_KICK_OTHER == playMode
				|| PLAY_MODE_GOAL_KICK_OTHER == playMode
				|| PLAY_MODE_GOAL_OTHER == playMode
				|| PLAY_MODE_GOAL_OWN == playMode) {
			player.move(0, 0);
			playerObjective = WAIT;
		} else if (!skipEval) {

			if (ballState == BALL_LOST)
				playerObjective = FOCUS;

			if (ballState == BALL_FOUND)
				playerObjective = GOTOBALL;

			if (ballState == BALL_CLOSE) {
				if (goalLastSeen > 1) {
					playerObjective = FOCUS;
					return;
				} else {
					if (goalDistance < 30) {
						playerObjective = SHOOT;
					} else {
						playerObjective = DRIBBLE;
					}
				}
			}
		} else {
			playerObjective = WAIT;
		}
	}

	@Override
	public void postInfo() {
		if (playerObjective == GOTOBALL) {
			player.turn(ballDirection);
			if (ballDistance > 2) {
				player.dash(100);
				player.dash(100);
			} else {
				player.dash(100);
			}
			ballState = BALL_LOST;
		}
		if (playerObjective == DRIBBLE) {
			player.kick(80, goalDirection);
			ballState = BALL_LOST;
		}
		if (playerObjective == SHOOT) {
			player.kick(100, goalDirection);
			ballState = BALL_LOST;
		}
		if (playerObjective == FOCUS) {
			player.turn(85);
			ballDirection += 85;
			goalDirection += 85;
		}
		if (playerObjective != WAIT)
			skipEval = true;
		else
			skipEval = false;
	}

	@Override
	public void infoHearPlayMode(int playmode) {
		this.playMode = playmode;
	}

	@Override
	public void infoSeeBall(double distance, double direction) {
		this.ballDistance = distance;
		this.ballDirection = direction;
		if (distance < 0.5)
			this.ballState = BALL_CLOSE;
		else
			this.ballState = BALL_FOUND;
	}

	@Override
	public void infoSeeFlagGoalOther(int id, double distance, double direction) {
		if (id == FLAG_CENTER) {
			this.goalDirection = direction;
			this.goalDistance = distance;
			this.goalLastSeen = 0;
		}
	}

	@Override
	public void infoSeeFlagGoalOwn(int arg0, double arg1, double arg2) {
	}

}
