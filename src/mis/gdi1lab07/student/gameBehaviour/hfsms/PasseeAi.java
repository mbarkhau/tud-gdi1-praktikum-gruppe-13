package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.ConstantValue;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Wait;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAknowledgement;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PasseeAi<T extends GameEnv> extends StudentHFSM<T> {

	public PasseeAi(FieldPlayer<T> player) throws AutomatonException {

		StudentHFSM<T> walk = new WalkToBall<T>(player);
		StudentHFSM<T> watchBall = new WatchBall<T>(player);
		StudentHFSM<T> acceptPass = new AcceptPassFrom<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);

		setInitialState(watchBall);

		addState(watchBall);
		addState(walk);
		addState(acceptPass);
		addState(wait);

		addTransition(watchBall.getName(), acceptPass.getName(), "accept pass",
				new HasHeardRequest<T>((T) player.getEnv()));
		addTransition(acceptPass.getName(), watchBall.getName(), "goto ball",
				new ConstantValue<T>(true));
		addTransition(watchBall.getName(), walk.getName(), "goto ball",
				new HasHeardAknowledgement<T>((T) player.getEnv()));
		addTransition(walk.getName(), wait.getName(), "got ball",
				new BallInDistance<T>((T) player.getEnv(), 0.5));
	}
}
