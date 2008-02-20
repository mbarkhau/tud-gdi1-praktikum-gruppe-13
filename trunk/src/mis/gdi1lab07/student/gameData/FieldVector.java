package mis.gdi1lab07.student.gameData;

public class FieldVector {
	
	private int age = 0;
	
	private double distance = 0;
	
	private double direction = 0;

	public FieldVector(double distance, double direction) {
		this.distance = distance;
		this.direction = direction;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	/** Werde älter! */
	public void doAge(){
		age++;
	}
	
	public int getAge(){
		return age;
	}
	
	public String toString(){
		return "distance: " + distance + " direction: " + direction;
	}
	
}
