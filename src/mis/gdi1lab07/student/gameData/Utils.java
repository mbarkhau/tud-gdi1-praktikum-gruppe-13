package mis.gdi1lab07.student.gameData;

public class Utils {

	// TODO: prüfe ob das ausreicht (ungenauigkeit von richtungen könnte zu groß sein)
	public static double DIR_DELTA = 3;
	
	/** @return the distance between two vectors */
	public static double getVectorDistance(FieldVector a, FieldVector b) {
		if (a == null || b == null)
			return 9999;
		return getVectorDistance(a.getDistance(), a.getDirection(), b.getDistance(), b.getDirection());
	}
	
	public static double getVectorDistance(double distanceA,
			double angleA, double distanceB, double angleB) {
		double angleAB = 0;
		if ((angleA > 0  && angleB > 0) || (angleA < 0 && angleB < 0))
			angleAB = Math.abs(angleA - angleB);
		else
			angleAB = Math.abs(angleA) + Math.abs(angleB);
		
		return Math.sqrt(Math.pow(distanceA, 2) + Math.pow(distanceB, 2)
				- 2 * distanceA * distanceB
				* Math.cos(Math.toRadians(angleAB)));
	}

	/** @return whether two values are very close too each other. */
	public static boolean inDelta(double a, double b){
		return inDelta(a, b, DIR_DELTA);
	}
	
	/** @return whether two values are within delta distance from each other. */
	public static boolean inDelta(double a, double b, double delta){
		return Math.abs(a - b) < delta;
	}
	
	/** Conversion for kick power. */
	public static int convertDistToPow(double distance){
		return new Double(distance * 2).intValue();
	}
	
	/** Conversion for run distance. */
	public static double convertPowToDist(int pow){
		return pow / 100;
	}

	/** Turn the vector by angle and return the modified vector */
	public static FieldVector turnVector(double angle, FieldVector v){
		double result = v.getDirection();

		// Noramlisieren des Eingang-Winkels
		if (angle > 180) {
			angle -= 360;
		} else if (angle <= -180)
			angle += 360;

		// Drehung
		result -= angle;

		// Normalisierung des Ausgang-Winkels
		if (result > 180)
			result -= 360;
		else if (result <= -180)
			result += 360;

		v.setDirection(result);
		return v;
	}
	
	/** displace the vector according to the acceleration and return the modified vector. */
	public static FieldVector displaceVector(int pow, FieldVector v){
		double a1 = v.getDirection();
		double d1 = v.getDistance();
		//die verschiebung in y richtung
		double deltaY = convertPowToDist(pow); 
		
		//Noramlisieren des Eingang-Winkels
		double an = (a1 >= 0) ? 90 - a1 : Math.abs(a1) + 90;
		double y1 = Math.sin(Math.toRadians(an)) * d1;
		double y2 = y1 - deltaY;
		double x = Math.cos(Math.toRadians(an)) * d1;
		double a2 = Math.toDegrees(Math.atan(y2/x));
		a2 = (a1 >= 0) ? 90 - a2 : - (90 + a2);
		
		v.setDirection(a2);
		v.setDistance(Math.sqrt(Math.pow(x, 2) + Math.pow(y2, 2)));
		return v;
	}
}
