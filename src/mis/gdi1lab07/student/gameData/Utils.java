package mis.gdi1lab07.student.gameData;

public class Utils {

	// TODO: prüfe ob das ausreicht (ungenauigkeit von richtungen könnte zu groß sein)
	public static double DIR_DELTA = 5;
	
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

	public static boolean isDirectionEqual(double dirA, double dirB){
		return Math.abs(dirA) - Math.abs(dirB) < DIR_DELTA;
	}
	
}
