package mis.gdi1lab07.student.gameData;

/**
 * Vector for field positions. Values represent the delta a position would
 * travel during one game tick.
 */
public class FieldDirection {

	private final double xVelocity;

	private final double yVelocity;

	public FieldDirection(double xVelocity, double yVelocity) {
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	public double getXVelocity() {
		return xVelocity;
	}

	public double getYVelocity() {
		return yVelocity;
	}
}
