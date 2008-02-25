package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAck;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PasseeAi<T extends GameEnv> extends BaseHfsm<T> {

	public PasseeAi(FieldPlayer<T> player) throws AutomatonException {
		super(player);

		// states
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> watchBall = new LookAtBall<T>(player);
		StudentHFSM<T> acceptPass = new PassRespond<T>(player);
		StudentHFSM<T> gotoBall = new GotoBall<T>(player);

		addState(scout);
		addState(watchBall);
		addState(acceptPass);
		addState(gotoBall);
		setInitialState(scout);
		
		// expressions		
		LogicExpression<T> heardRequest = new HasHeardRequest<T>(env);
		LogicExpression<T> heardAck = new HasHeardAck<T>(env);
		LogicExpression<T> notHeardAck = new NotExpression<T>(heardAck);
		LogicExpression<T> atBall = new BallInDistance<T>(env, 0.5);
		
		// transitions
		addTransition(scout, acceptPass, heardRequest);
		addTransition(watchBall, gotoBall, heardAck);
		addTransition(acceptPass, gotoBall, heardAck);
		addTransition(acceptPass, scout, notHeardAck);
		addTransition(gotoBall, scout, atBall);
	}

	@Override
	public void doOutput() throws AutomatonException {
		// noop
	}
	
	@Override
	public void reset() throws AutomatonException {
		super.reset();
	}
}
