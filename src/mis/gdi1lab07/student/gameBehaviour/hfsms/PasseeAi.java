package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
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

		StudentHFSM<T> wait = new Wait<T>(player);
		StudentHFSM<T> watchBall = new LookAtBall<T>(player);
		StudentHFSM<T> acceptPass = new AcceptPassFrom<T>(player);
		StudentHFSM<T> gotoBall = new GotoBall<T>(player);

		addState(wait);
		addState(watchBall);
		addState(acceptPass);
		addState(gotoBall);
		
		setInitialState(watchBall);
		
		LogicExpression<T> heardRequest = new HasHeardRequest<T>(player.getEnv());
		LogicExpression<T> heardAck = new HasHeardAknowledgement<T>(player.getEnv());
		LogicExpression<T> notHeardAck = new NotExpression<T>(heardAck);
		LogicExpression<T> atBall = new BallInDistance<T>(player.getEnv(), 0.5);
		
		addTransition(watchBall, acceptPass, heardRequest);
		addTransition(watchBall, gotoBall, heardAck);
		
		addTransition(acceptPass, gotoBall, heardAck);
		addTransition(acceptPass, watchBall, notHeardAck);
		
		addTransition(gotoBall, wait, atBall);
	}
}
