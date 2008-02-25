package mis.gdi1lab07.student.gameData;

import atan2.model.Controller;
import atan2.model.Player;

/**
 * Decorates an {@link atan2.model.Player} so the enviroment can be updated,
 * when commands are issued.
 */
public class FieldPlayer<T extends GameEnv> implements Player {

	private Player decoratedPlayer;

	private String name;

	private String teamName;

	private T env;

	public FieldPlayer(Player player, T env) {
		this.decoratedPlayer = player;
		this.env = env;
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
		env.dash(power);
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
		System.out.println("Player " + decoratedPlayer.getNumber() + " said "
				+ message);
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
		env.turn(angle);
		decoratedPlayer.turn(angle);
	}

	public T getEnv() {
		return env;
	}

}