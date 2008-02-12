package mis.gdi1lab07.student.gameData;

/** Representation of the sighting of a flag */
public class FlagSighting {
	
	// TODO: Consider updating distance and direction, to keep these valid.
	// Will only be necessary, if there arn't enough sightings
	private final Flag flag;
	
	private double distance;
	
	private double direction;

	public FlagSighting(Flag flag, double distance, double direction) {
		this.flag = flag;
		this.distance = distance;		
		this.direction = direction;
	}
	
	public Flag getFlag() {
		return flag;
	}

	public double getDistance() {
		return distance;
	}

	public double getDirection() {
		return direction;
	}
}
