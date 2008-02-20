package mis.gdi1lab07.student.gameData;

import java.util.LinkedList;
import java.util.List;

public class GameEnv {

	FieldVector ball = null;

	// um richtung
	List<FieldVector> vectors = new LinkedList<FieldVector>();
	
	
	public FieldVector getBall() {
		return ball;
	}

	public void setBall(FieldVector ball) {
		vectors.add(ball);
		this.ball = ball;
	}
	
	/**
	 * @param angle mit dem sich der spieler gedreht.
	 */
	public void turn(double angle){
		for (FieldVector v : vectors) {
			double result = v.getDirection();
			// Normalisiere dreh Winkel
			if (angle < 0)
				angle = - (Math.abs(angle) % 360);

			result -= angle;
			
			// Normalisiere ergebnis
			if (result > 180)
				result = - (360 - result);
			if (result < -180)
				result = - (360 + result);

			v.setDirection(result);
		}
	}
	
}
