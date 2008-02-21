package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedToMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PasseeAi<T extends GameEnv> extends StudentHFSM<T> {

	public PasseeAi(FieldPlayer player) throws AutomatonException {

		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> walk = new WalkToBall<T>(player);
		StudentHFSM<T> acceptPass = new AcceptPassFrom<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);

		setInitialState(scout);

		addState(scout);
		addState(walk);
		addState(acceptPass);
		addState(wait);

		addTransition(scout.getName(), acceptPass.getName(), "accept pass",
				new HasHeardRequest<T>((T) player.getEnv()));
		addTransition(acceptPass.getName(), walk.getName(), "goto ball",
				new BallPassedToMe<T>((T) player.getEnv()));
		addTransition(walk.getName(), wait.getName(), "got ball",
				new IsAtBall<T>((T) player.getEnv()));
	}
}
