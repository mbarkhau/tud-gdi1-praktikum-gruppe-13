package mis.gdi1lab07.student.gameBehaviour;

import mis.gdi1lab07.student.gameData.FieldPosition;
import mis.gdi1lab07.student.gameData.FieldPlayer;

public interface PlayerControl {

	/** Go back to the players designated Position. */
	public void goHome();

	/** Set the Fieldposition the player should operate around. */
	public void setHome(FieldPosition position);

	/**
	 * Gaurd the Ball from a player.<br>
	 * Try to intercept the Ball from the player, position between the player
	 * and the ball.
	 */
	public void gaurdPlayer(FieldPlayer player);
	
	/** Try to take the ball away from a player. */
	public void tacklPlayer(FieldPlayer player);
	
	/** Pass the ball to a player. */
	public void passToPlayer(FieldPlayer player);
	
	/* 
	 * Inner players try to hold a constant distance to their players
	 * Outer players try to move inward up to a certain threshold, 
	 * this should produce an even line.
	 * Note that the Manual indicates, that the collision detection works such,
	 * that if the Ball is fast enough, it can go through the wall uneffected. 
	 */
	/** Make a wall between the goal and the ball. */
	public void makeWall();
}
