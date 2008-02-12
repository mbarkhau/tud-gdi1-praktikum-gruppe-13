package mis.gdi1lab07.student.gameData;

/** Representation of an object on the PlayingField, */
public abstract class MobileFieldObject {
	// Internal fields always represent the last known position and velocity
	// Predictions should be calculated from that in subclasses.

	protected int updateTick;

	protected FieldPosition position;

	protected FieldDirection direction;

	/** Refresh the position and direction of the object, based on observation. */
	public void updatePos(int currentTick, FieldPosition curPos) {
		if ((currentTick - updateTick) < 5)
		this.direction = new FieldDirection(
				((curPos.getXPos() - position.getXPos()) * (currentTick - updateTick)),
				((curPos.getYPos() - position.getYPos()) * (currentTick - updateTick)));
		
		this.position = curPos;
		this.updateTick = currentTick;
	}

	/**
	 * Predict the position in the next Tick, based on current position and
	 * velocity.
	 */
	public abstract FieldPosition predictNextPos();

	/**
	 * Predict the position, based on current position and velocity.
	 * 
	 * @param ticks
	 *            to predict into the future
	 */
	public abstract FieldPosition predictPos(int ticks);

	public FieldPosition getPosition() {
		return position;
	}

	/** Explicitly set the position */
	public void setPosition(int currentTick, FieldPosition position) {
		this.position = position;
		this.direction = new FieldDirection(0, 0);
		this.updateTick = currentTick;
	}
}
