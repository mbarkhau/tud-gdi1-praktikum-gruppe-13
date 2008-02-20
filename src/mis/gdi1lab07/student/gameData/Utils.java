package mis.gdi1lab07.student.gameData;

public class Utils {

	public static double getPlayerDistanceToBall(double distanceBall,
			double angleBall, double distancePlayer, double anglePlayer) {
		// System.out.println("distanceBall: " + distanceBall + "angleBall "
		// + angleBall + "distancePlayer " + distancePlayer
		// + "anglePlayer" + anglePlayer);

		return Math.sqrt(Math.pow(distanceBall, 2) + Math.pow(distanceBall, 2)
				- 2 * distanceBall * distancePlayer
				* Math.cos(Math.abs(angleBall - anglePlayer)));
	}

}
