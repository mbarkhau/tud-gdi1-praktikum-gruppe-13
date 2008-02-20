package mis.gdi1lab07.student.gameData;

public class Utils {

	// TODO: prüfe ob das ausreicht (ungenauigkeit von richtungen könnte zu groß sein)
	double DIR_DELTA = 2;
	
	/** @return the distance between two vectors */
	public static double getVectorDistance(FieldVector a, FieldVector b) {
		return getVectorDistance(a.getDistance(), a.getDirection(), b.getDistance(), b.getDirection());
	}
	
	public static double getVectorDistance(double distanceBall,
			double angleBall, double distancePlayer, double anglePlayer) {
		// System.out.println("distanceBall: " + distanceBall + "angleBall "
		// + angleBall + "distancePlayer " + distancePlayer
		// + "anglePlayer" + anglePlayer);

		return Math.sqrt(Math.pow(distanceBall, 2) + Math.pow(distanceBall, 2)
				- 2 * distanceBall * distancePlayer
				* Math.cos(Math.abs(angleBall - anglePlayer)));
	}

	public boolean isDirectionEqual(double dirA, double dirB){
		return Math.abs(dirA - dirB) < DIR_DELTA;
	}
	
}
