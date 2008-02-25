package mis.gdi1lab07.student.gameData;

import atan2.model.ControllerAdaptor;

public class Utils implements FlagConstants {

	// TODO: prüfe ob das ausreicht (ungenauigkeit von richtungen könnte zu groß sein)
	public static double DIR_DELTA = 3;
	
	public static double KICK_DIST_POW_FACTOR = 2;

	public static double DASH_POW_DIST_FACTOR = 0.01;
	
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
		return new Double(distance * KICK_DIST_POW_FACTOR).intValue();
	}
	
	/** Conversion for run distance. */
	public static double convertPowToDist(int pow){
		return pow * DASH_POW_DIST_FACTOR;
	}

	/** @return wether player.move(x,y) can be used in the playmode */
	public static boolean canUseMove(int playMode){
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
	
	/** @return the designated position for the player number*/
	public static int getPlayerPos(int playerNr){
		//Wei�t jedem Spieler anhand seiner ID eine Position zu, zu der er 
		//geht, wenn seine Defensiv-KI zur Startposition geht.
		
		int[] positions = {INVALD, T_G_C, T_P_L, T_P_C, T_P_R, C_O_L, C, C, C_O_R, O_P_L, O_P_C, O_P_R};
		if (playerNr > 11 || playerNr < 1)
			return INVALD;
			
		return positions[playerNr];
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
