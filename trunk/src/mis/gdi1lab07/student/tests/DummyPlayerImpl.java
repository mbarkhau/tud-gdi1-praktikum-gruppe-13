package mis.gdi1lab07.student.tests;

import atan2.model.Controller;
import atan2.model.Player;

public class DummyPlayerImpl implements Player {

	private int number;
		
	public DummyPlayerImpl(int number) {
		this.number = number;
	}
	
	@Override
	public void bye() {
		// TODO Auto-generated method stub

	}

	@Override
	public void catchBall(double direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dash(int power) {
		// TODO Auto-generated method stub

	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public String getTeamName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleError(String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isTeamEast() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void kick(int power, double direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void say(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNumber(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTeamEast(boolean is) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skipCommands() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(double angle) {
		// TODO Auto-generated method stub

	}

}
