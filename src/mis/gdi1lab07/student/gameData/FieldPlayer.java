package mis.gdi1lab07.student.gameData;

import java.util.HashMap;
import java.util.Map;

import atan2.model.Controller;
import atan2.model.Player;

public class FieldPlayer extends MobileFieldObject implements Player {

	private Player decoratedPlayer;

	private String name;

	private String teamName;

	private Boolean goalie = false;

//	private Integer stamina = 4000;
//
//	private double viewOffset = 0;

	private GameEnv env;
	
	// Used for triangulation, entries in this List MUST be as precise as
	// possible relative to the player. Either refresh them, if the Player
	// moves/turns, or remove all entries.
	private Map<Flag, FlagSighting> sightings = new HashMap<Flag, FlagSighting>();

	public void recordFlagSighting(Integer tick, FlagSighting sighting) {
		sightings.put(sighting.getFlag(), sighting);
		FieldPosition newPos = null;

	}

	public FieldPlayer(Player player) {
		this.decoratedPlayer = player;
	}

	@Override
	public FieldPosition predictNextPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FieldPosition predictPos(int ticks) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Boolean getGoalie() {
		return goalie;
	}

	public void setGoalie(Boolean goalie) {
		this.goalie = goalie;
	}

	// Decorated Methods

	@Override
	public void bye() {
		decoratedPlayer.bye();
	}

	@Override
	public void catchBall(double direction) {
		decoratedPlayer.catchBall(direction);
	}

	@Override
	public void dash(int power) {
		decoratedPlayer.dash(power);
	}

	@Override
	public Controller getController() {
		return decoratedPlayer.getController();
	}

	@Override
	public int getNumber() {
		return decoratedPlayer.getNumber();
	}

	@Override
	public void handleError(String error) {
		decoratedPlayer.handleError(error);
	}

	@Override
	public boolean isTeamEast() {
		return decoratedPlayer.isTeamEast();
	}

	@Override
	public void kick(int power, double direction) {
		decoratedPlayer.kick(power, direction);

	}

	@Override
	public void move(int x, int y) {
		decoratedPlayer.move(x, y);
	}

	@Override
	public void say(String message) {
		decoratedPlayer.say(message);

	}

	@Override
	public void setNumber(int num) {
		decoratedPlayer.setNumber(num);

	}

	@Override
	public void setTeamEast(boolean is) {
		decoratedPlayer.setTeamEast(is);

	}

	@Override
	public void skipCommands() {
		decoratedPlayer.skipCommands();

	}

	@Override
	public void turn(double angle) {
		decoratedPlayer.turn(angle);
		env.turn(angle);
	}

	public GameEnv getEnv() {
		return env;
	}

	public void setEnv(GameEnv env) {
		this.env = env;
	}

}
