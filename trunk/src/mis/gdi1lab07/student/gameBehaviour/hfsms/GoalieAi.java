package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GoalieAi<T extends GameEnv> extends StudentHFSM<T> {
	
	public GoalieAi(FieldPlayer<T> player) throws AutomatonException {
		
		player.setNumber(1);
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> gotoGoal = new GoToStartPosition<T>(player);
		StudentHFSM<T> watchBall = new WatchBall<T>(player);
		StudentHFSM<T> grabBall = new WatchBall<T>(player);
		StudentHFSM<T> passer = new PasserAi<T>(player);
		StudentHFSM<T> kick = new WatchBall<T>(player);
		StudentHFSM<T> gotoBall = new WatchBall<T>(player);

		addState(scout);
		addState(gotoGoal);
		addState(watchBall);
		addState(grabBall);
		addState(passer);
		addState(kick);
		addState(gotoBall);
		
		setInitialState(scout);
		
		
	}
}
