package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtPlayer;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HeardResponse;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtPlayer;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PasserAi<T extends GameEnv> extends BaseHfsm<T> {

	public PasserAi(FieldPlayer<T> player) throws AutomatonException {
		super(player);

		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> gotoBall = new GotoBall<T>(player);
		StudentHFSM<T> kickToPlayer = new KickToPlayer<T>(player);
		StudentHFSM<T> request = new PassRequest<T>(player);
		StudentHFSM<T> acknowledge = new PassAck<T>(player);
		StudentHFSM<T> findPassee = new FindPassee<T>(player);
		StudentHFSM<T> lookAtPassee = new LookAtPlayer<T>(player, true, -1);

		setInitialState(scout);

		addState(scout);
		addState(gotoBall);
		addState(kickToPlayer);
		addState(request);
		addState(acknowledge);
		addState(findPassee);
		addState(lookAtPassee);
		
		LogicExpression<T> atBall = new BallInDistance<T>(env, 1);
		LogicExpression<T> notAtBall = new NotExpression<T>(atBall);
		
		LogicExpression<T> anyPlayerVisible = new LookingAtPlayer<T>(env, true, -2);
		LogicExpression<T> noPlayerVisible = new NotExpression<T>(anyPlayerVisible);
		
		LogicExpression<T> canRequest = new AndExpression<T>(atBall, anyPlayerVisible);
		LogicExpression<T> cannotRequest = new AndExpression<T>(atBall, noPlayerVisible);
		
		LogicExpression<T> heardResponse = new HeardResponse<T>(env);
		
		LogicExpression<T> canPassToPlayer = new AndExpression<T>(atBall, heardResponse);

		LogicExpression<T> passeeVisible = new LookingAtPlayer<T>(env, true, -2);
		
		LogicExpression<T> passedByMe = new BallPassedByMe<T>(env);
		

		addTransition(scout, gotoBall, notAtBall);
		
		addTransition(kickToPlayer, gotoBall, notAtBall);
		addTransition(request, gotoBall, notAtBall);
		addTransition(acknowledge, gotoBall, notAtBall);
		addTransition(findPassee, gotoBall, notAtBall);
		addTransition(lookAtPassee, gotoBall, notAtBall);
		
		addTransition(gotoBall, findPassee, cannotRequest);
		addTransition(gotoBall, request, canRequest);

		addTransition(findPassee, lookAtPassee, canPassToPlayer);
		addTransition(request, lookAtPassee, canPassToPlayer);
		
		addTransition(lookAtPassee, acknowledge, passeeVisible);
		
		addTransition(acknowledge, kickToPlayer, canPassToPlayer);
		
		addTransition(gotoBall, scout, passedByMe);
	}
	
	@Override
	public void reset() throws AutomatonException {
		super.reset();
		env.setHfsmParam(PASSER_HAS_PASSED, new Boolean(false));
	}

	@Override
	public void doOutput() throws AutomatonException {
		//noop
	}
}