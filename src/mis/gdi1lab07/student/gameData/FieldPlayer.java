package mis.gdi1lab07.student.gameData;

import atan2.model.Controller;
import atan2.model.Player;

/**
 * Decorates an {@link atan2.model.Player} so the enviroment can be updated,
 * when commands are issued.
 */
public class FieldPlayer<T extends GameEnv> implements Player {

	private double POWER_NORMAL_FACTOR = 1;
	
	private double POWER_TIRED_FACTOR = 0.75;
	
	private double POWER_EXAUSTED_FACTOR = 0;
	
	private double curPowFactor = POWER_NORMAL_FACTOR;
	
	private int tickPowFactorSet = 0;
	
	private Player decoratedPlayer;

	private String name;

	private String teamName;

	private T env;
	
	public FieldPlayer(Player player, T env) {
		this.decoratedPlayer = player;
		this.env = env;
		this.env.setPlayerId(player.getNumber());
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
		return decoratedPlayer.getController().isGoalie();
	}
	
	/**
	 * Wenn Nummer 1, dann soll Spieler Goalie werden. Wichtig f�r Konstruktor
	 */
	public boolean isThisNumberAGoalie() {
		return getNumber()==1;
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
		power = getAllowedPower(power);
		if (power > 0){
			decoratedPlayer.dash(power);
			env.dash(power);
		}
	}

	private int getAllowedPower(int power){
		double stamina = env.getStamina();
		boolean canSetHigher = (env.getTick() - tickPowFactorSet) > 150;
		if(stamina < 40 && curPowFactor > POWER_EXAUSTED_FACTOR){
			curPowFactor = POWER_EXAUSTED_FACTOR;
			tickPowFactorSet = env.getTick();
		}else if((stamina < 1000 || canSetHigher && stamina > 1000) && curPowFactor > POWER_TIRED_FACTOR){
			curPowFactor = POWER_TIRED_FACTOR;
			tickPowFactorSet = env.getTick();
		}else if(canSetHigher && stamina > 2000){
			curPowFactor = POWER_NORMAL_FACTOR;
			tickPowFactorSet = env.getTick();
		}
		return new Double(power * curPowFactor).intValue();
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
		if(Utils.debugThis(Utils.DBG_ALL))
		System.out.println(env.getTick() + " " + getNumber() + " says " + message);
		decoratedPlayer.say(message);
	}

	@Override
	public void setNumber(int num) {
		decoratedPlayer.setNumber(num);
		this.env.setPlayerId(num);
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
		env.turn(angle);
		decoratedPlayer.turn(angle);
	}

	public T getEnv() {
		return env;
	}

}
