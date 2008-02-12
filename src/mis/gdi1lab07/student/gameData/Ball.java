package mis.gdi1lab07.student.gameData;

/** Representation of the Ball for tracking purposes. */
public class Ball extends MobileFieldObject {
	public Ball() {
		this.position = new FieldPosition(0, 0);
		this.direction = new FieldDirection(0, 0);
	}
	
	@Override
	public FieldPosition predictNextPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FieldPosition predictPos(int ticks) {
		// TODO Auto-generated method stub
		return null;
	}	
}
