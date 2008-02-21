package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GoalieAi<T extends GameEnv> extends StudentHFSM<T> {
	
	public GoalieAi(FieldPlayer player) {
		
		StudentHFSM<T> watchBall = new WatchBall<T>(player);
		
		StudentHFSM<T> scout = new Scout<T>(player);
		
		
	}
}
