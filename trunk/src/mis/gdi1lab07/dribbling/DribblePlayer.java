package mis.gdi1lab07.dribbling;

import atan2.model.ControllerAdaptor;

/**
 * This class must be implemented by the students to create a player with dribbling
 * capabilities.
 */
public class DribblePlayer extends ControllerAdaptor {

	@Override
	public void preInfo() {
		System.out.println("preInfo: ");
	}
	
	@Override
	public void postInfo() {
		System.out.println("postInfo: ");
	}
	
	@Override
	public void infoHearPlayMode(int playmode) {
		System.out.println("playmode: " + playmode);
	}
	
	@Override
	public void infoSeeBall(double arg0, double arg1) {
		System.out.println("infoSeeBall: ");
	}
	
	@Override
	public void infoSeeFlagGoalOther(int arg0, double arg1, double arg2) {
		System.out.println("infoSeeFlagGoalOther: ");
	}
	
	@Override
	public void infoSeeFlagGoalOwn(int arg0, double arg1, double arg2) {
		System.out.println("infoSeeFlagGoalOwn: ");
	}
	
}
