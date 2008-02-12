package mis.gdi1lab07.student.gameData;

import java.util.Calendar;

import atan2.model.Controller;

/** State of the playing field. */
public class PlayingField {

	public static double WIDTH = 105;

	public static double HEIGHT = 68;

	public static double BOUND = 5;

	private FieldDirection windDirection;

	private Integer gameMode;

	private String ourName;

	private String theirName;

	private Integer ourScore;

	private Integer theirScore;

	// The amount of ticks passed, before the lastTickStart
	private Integer tickCount = 0;
	
	// One tick is 100ms long, and can thus be calculated on the fly
	private Long lastTickStart;

	public PlayingField() {
		this.lastTickStart = Calendar.getInstance().getTimeInMillis();
	}
	
	public FieldDirection getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(FieldDirection windDirection) {
		this.windDirection = windDirection;
	}

	public Integer getGameMode() {
		return gameMode;
	}

	/** TODO: */
	public void setGameMode(Integer gameMode) {
		this.gameMode = gameMode;
		if (gameMode == Controller.PLAY_MODE_BEFORE_KICK_OFF) {
			tickCount = 0;
		}
		if (gameMode == Controller.PLAY_MODE_KICK_OFF_OWN
				|| gameMode == Controller.PLAY_MODE_GOAL_KICK_OTHER){
			tickCount = 0;
			lastTickStart = Calendar.getInstance().getTimeInMillis();
		}
		if (gameMode == Controller.PLAY_MODE_GOAL_OWN) {
			ourScore++;
		}
	}

	public Integer getCurrentTick(){
		Long now = Calendar.getInstance().getTimeInMillis();
		Long timeDelta = now - lastTickStart;
		Integer tickDelta = Math.round(timeDelta / 100);
		return tickCount + tickDelta;
	}
	
	public String getOurName() {
		return ourName;
	}

	public void setOurName(String ourName) {
		this.ourName = ourName;
	}

	public String getTheirName() {
		return theirName;
	}

	public void setTheirName(String theirName) {
		this.theirName = theirName;
	}

	public Integer getOurScore() {
		return ourScore;
	}

	public void setOurScore(Integer ourScore) {
		this.ourScore = ourScore;
	}

	public Integer getTheirScore() {
		return theirScore;
	}

	public void setTheirScore(Integer theirScore) {
		this.theirScore = theirScore;
	}

	//TODO: Calculate this on the fly
	public Integer getTickCount() {
		return tickCount;
	}
}
