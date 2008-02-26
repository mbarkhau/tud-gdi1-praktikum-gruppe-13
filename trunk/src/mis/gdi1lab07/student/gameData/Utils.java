package mis.gdi1lab07.student.gameData;

import atan2.model.ControllerAdaptor;

public class Utils implements FlagConstants {

	public static double DIR_DELTA = 2;

	public static double KICK_DIST_POW_FACTOR = 3;

	public static double DASH_POW_DIST_FACTOR = 0.01;
	
	public static int DBG_ALL = 100;
	
	public static int DBG_NONE = 0;
	
	public static int DEBUG_LEVEL = DBG_NONE;

	/** @return the distance between two vectors */
	public static double getVectorDistance(FieldVector a, FieldVector b) {
		if (a == null || b == null)
			return 9999;
		return getVectorDistance(a.getDist(), a.getDir(), b
				.getDist(), b.getDir());
	}

	public static double getVectorDistance(double distanceA, double angleA,
			double distanceB, double angleB) {
		double angleAB = addAngles(angleA, angleB);

		return Math.sqrt(sqr(distanceA) + sqr(distanceB) - 2
				* distanceA * distanceB * cos(angleAB));
	}

	/** @return whether two values are very close too each other. */
	public static boolean inDelta(double a, double b) {
		return inDelta(a, b, DIR_DELTA);
	}

	/** @return whether two values are within delta distance from each other. */
	public static boolean inDelta(double a, double b, double delta) {
		return Math.abs(a - b) < delta;
	}

	/** Conversion for kick power. */
	public static int convertDistToPow(double distance) {
		return new Double(distance * KICK_DIST_POW_FACTOR).intValue();
	}

	/** Conversion for run distance. */
	public static double convertPowToDist(int pow) {
		return pow * DASH_POW_DIST_FACTOR;
	}

	/** @return wether player.move(x,y) can be used in the playmode */
	public static boolean canUseMove(int playMode) {
		switch (playMode) {
		case ControllerAdaptor.PLAY_MODE_BEFORE_KICK_OFF:
			return true;
		case ControllerAdaptor.PLAY_MODE_GOAL_OTHER:
			return true;
		case ControllerAdaptor.PLAY_MODE_GOAL_OWN:
			return true;
		case ControllerAdaptor.PLAY_MODE_TIME_OVER:
			return true;
		default:
			return false;
		}
	}

	/** @return the designated position for the player number */
	public static int getPlayerPos(int playerNr) {
		// Wei�t jedem Spieler anhand seiner ID eine Position zu, zu der er
		// geht, wenn seine Defensiv-KI zur Startposition geht.

		int[] positions = { INVALD, O_G_C, T_P_L, T_P_C, T_P_C, T_P_C, T_P_R, 
				C_O_L, C_O_R, C, O_P_L, O_P_R };
		if (playerNr > 11 || playerNr < 1)
			return INVALD;

		return positions[playerNr];
	}

	/** Turn the vector by angle and return the modified vector */
	public static FieldVector turnVector(double angle, FieldVector v) {
		double result = v.getDir();

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
	
	public static int getDebugLevel() {
		return DEBUG_LEVEL;
	}
	
	public static boolean debugThis(int threshold) {
		return (DEBUG_LEVEL>=threshold);
	}

	/**
	 * displace the vector according to the acceleration and return the modified
	 * vector.
	 */
	public static FieldVector displaceVector(int pow, FieldVector v) {
		double a1 = v.getDir();
		double d1 = v.getDist();
		// die verschiebung in y richtung
		double deltaY = convertPowToDist(pow);

		// Noramlisieren des Eingang-Winkels
		double an = (a1 >= 0) ? 90 - a1 : Math.abs(a1) + 90;
		double y1 = sin(an) * d1;
		double y2 = y1 - deltaY;
		double x = cos(an) * d1;
		double a2 = atan(y2 / x);
		a2 = (a1 >= 0) ? 90 - a2 : -(90 + a2);

		v.setDirection(a2);
		v.setDistance(Math.sqrt(sqr(x) + sqr(y2)));
		return v;
	}

	public static int findPlayerId(String msg) {
		for (int i = 0; i < GameMessages.ALL.length; i++) {
			String curMsg = GameMessages.ALL[i];
			if (msg.startsWith(curMsg)) {
				String speakerId = msg.substring(curMsg.length());
				return Integer.valueOf(speakerId);
			}
		}
		return -1;
	}
	
	/** 
	 * Calculate where a vector will be, according to two vectors last seen.
	 * @param ticks to predict into the future
	 */
	public static FieldVector predictVector(FieldVector a, FieldVector b, double ticks){
		
		double distAB = getVectorDistance(a, b);
		double distAC = distAB * (ticks + 1);
		double angleAPB = addAngles(a.getDir(), b.getDir());
		double angleBAP = asin((b.getDist() * sin(angleAPB)) / distAC);
		
		double distC = Math.sqrt(sqr(a.getDist()) + sqr(distAC) - 2 * a.getDist() * distAC * cos(angleBAP));
		double dirC = asin((distAC * sin(angleBAP) / distC));
		dirC += (a.getDir() < 0) ? a.getDir() : - a.getDir(); 
		return new FieldVector(distC, dirC);
	}
	
	// helper methods
	
	public static double sqr(double a){
		return Math.pow(a, 2);
	}

	public static double sin(double a){
		return Math.sin(Math.toRadians(a));
	}
	
	public static double asin(double a){
		return Math.toDegrees(Math.asin(a));
	}
	
	public static double cos(double a){
		return Math.cos(Math.toRadians(a));
	}
	
	public static double acos(double a){
		return Math.toDegrees(Math.acos(a));
	}
	
	public static double tan(double a){
		return Math.tan(Math.toRadians(a));
	}
	
	public static double atan(double a){
		return Math.toDegrees(Math.atan(a));
	}
	
	public static double addAngles(double a, double b){
		return ((a > 0 && b > 0) || (a < 0 && b < 0)) ?	Math.abs(a - b) : Math.abs(a) + Math.abs(b);
	}
}
