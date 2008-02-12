package mis.gdi1lab07.student.gameData;

/** Data for objects on the field */
public class FieldPosition {

	private final double xPos;

	private final double yPos;

	public FieldPosition(double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * Calculate a new position, relative to another.
	 * @param dir relative to the x axis
	 */
	public FieldPosition(FieldPosition pos, double dir, double distance){
		this.xPos = pos.getXPos() + (Math.cos(dir) * distance);
		this.yPos = pos.getYPos() + (Math.sin(dir) * distance);
	}
	
	public double getXPos() {
		return xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public String toString(){
		return "(" + xPos + ", " + yPos + ") ";
	}
}
