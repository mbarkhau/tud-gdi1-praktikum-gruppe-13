package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.ConstantValue;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Wait;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAknowledgement;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PasseeAi<T extends GameEnv> extends StudentHFSM<T> {

	public PasseeAi(FieldPlayer<T> player) throws AutomatonException {

		StudentHFSM<T> walk = new GotoBall<T>(player);
		StudentHFSM<T> watchBall = new LookAtBall<T>(player);
		StudentHFSM<T> acceptPass = new AcceptPassFrom<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);

		setInitialState(watchBall);

		addState(watchBall);
		addState(walk);
		addState(acceptPass);
		addState(wait);
		
		LogicExpression<T> constTrue = new ConstantValue<T>(true);
		LogicExpression<T> heardRequest = new HasHeardRequest<T>((T) player.getEnv());
		LogicExpression<T> heardAck = new HasHeardAknowledgement<T>((T) player.getEnv());
		LogicExpression<T> atBall = new BallInDistance<T>((T) player.getEnv(), 0.5);
		
		addTransition(watchBall, acceptPass, heardRequest);
		addTransition(acceptPass, watchBall, constTrue);
		addTransition(watchBall, walk, heardAck);
		addTransition(walk, wait, atBall);
	}
}
