package mis.gdi1lab07.student.gameBehaviour;

import atan2.model.Controller;
import mis.gdi1lab07.student.gameData.FieldPosition;

/** 
 * All commands to the player should go through this interface, 
 * and the implementation, should keep the FieldPlayer updated. 
 */
public interface PlayerPrimitives {

	/** Run to a position, as fast as possible. */
	public void run(FieldPosition position);
	
	/** Walk to a position, while staying aware of suroundings. */
	public void walk(FieldPosition position);

	/** Kick the ball so that it lands close to a position. */
	public void kick(FieldPosition position);

	/** Kick the ball, so that its path crosses the position.*/
	public void shoot(FieldPosition position); 
	
	/** Kick the ball ahead and follow behind. */
	public void sprint();
	
	/** Kick the ball a short distance and follow behind. */
	public void dribble(FieldPosition position);
	
	/** Set the controller to issue commands to. */
	public void setController(Controller controller);
}
