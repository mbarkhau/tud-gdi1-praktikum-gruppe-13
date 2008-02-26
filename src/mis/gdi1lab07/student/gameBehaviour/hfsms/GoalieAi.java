package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GrabBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallVisible;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtFlag;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GoalieAi<T extends GameEnv> extends BaseHfsm<T> {

	public GoalieAi(FieldPlayer<T> player) throws AutomatonException {
		super(player);
		
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> gotoGoal = new GotoFlag<T>(player, O_G_C);
		StudentHFSM<T> watchBall = new LookAtBall<T>(player);		
		watchBall.setName("watchBall");
		StudentHFSM<T> grabBall = new GrabBall<T>(player);
		grabBall.setName("grabBall");
		StudentHFSM<T> passer = new PasserAi<T>(player);
		StudentHFSM<T> kick = new LookAtBall<T>(player);
		kick.setName("kick");
		StudentHFSM<T> gotoBall = new GotoBall<T>(player);
		gotoBall.setName("gotoBall");

		addState(scout);
		addState(gotoGoal);
		addState(watchBall);
		addState(grabBall);
		addState(passer);
		addState(kick);
		addState(gotoBall);

		setInitialState(scout);
		
		LogicExpression<T> atGoal = new FlagInDistance<T>(env, O_G_C, 4);
		LogicExpression<T> notAtGoal = new NotExpression<T>(atGoal);
		
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> hasNotScouted = new NotExpression<T>(hasScouted);
		
		LogicExpression<T> ballVisible = new BallVisible<T>(env);
		LogicExpression<T> ballNotVisible = new NotExpression<T>(ballVisible);
		
		//Goalie im Strafraum, wenn er mittlere Strafraumflagge sieht, aber nicht mittlere Torflagge, oder umgekehrt
		LogicExpression<T> goalInView = new LookingAtFlag<T>(env, O_G_C);
		LogicExpression<T> goalNotInView = new NotExpression<T>(goalInView);
		LogicExpression<T> penaultyFlagInView = new LookingAtFlag<T>(env, O_P_C);
		LogicExpression<T> penaultyFlagNotInView = new NotExpression<T>(penaultyFlagInView);
		
		LogicExpression<T> isInPenaltyArea1 = new AndExpression<T>(goalInView, penaultyFlagNotInView);
		LogicExpression<T> isInPenaltyArea2 = new AndExpression<T>(goalNotInView, penaultyFlagInView);
		LogicExpression<T> isInPenaltyArea = new OrExpression<T>(isInPenaltyArea1, isInPenaltyArea2);
		
		LogicExpression<T> ballInGrabDist = new BallInDistance<T>(env, 2);
		
		//Goalie darf Ball nehmen, wenn er im Strafraum ist und der Ball weniger als 2 Meter entfernt ist
		LogicExpression<T> readyToGrab = new AndExpression<T>(isInPenaltyArea, ballInGrabDist);
		
		//Feind attackiert, sobald er und der Ball weniger als 20 Meter vom Tor entfernt sind
		LogicExpression<T> ballInDangerDist = new BallInDistance<T>(env, 20);
		LogicExpression<T> playerInDangerDist = new PlayerInDistance<T>(env, false, 20);
		LogicExpression<T> enemyAttacks = new AndExpression<T>(ballInDangerDist, playerInDangerDist);
		
		//Goalie hat Ball gefangen, er ist somit weniger als 1 Meter entfernt
		LogicExpression<T> grabbedBall = new BallInDistance<T>(env, 1);
		
		//Wenn der Ball mehr als 40 Meter entfernt ist
		LogicExpression<T> ballAway = new NotExpression<T>(new BallInDistance<T>(env, 40));
		LogicExpression<T> returnToGoal = new AndExpression<T>(ballAway, notAtGoal);
		
		addTransition(scout, watchBall, atGoal);
		addTransition(watchBall, scout, hasNotScouted);
		addTransition(watchBall, grabBall, readyToGrab);
		addTransition(watchBall, gotoBall, enemyAttacks);
		addTransition(grabBall, passer, grabbedBall);
		addTransition(passer, gotoGoal, ballAway);
		addTransition(gotoBall, gotoGoal, ballAway);
		addTransition(grabBall, gotoGoal, ballAway);
		addTransition(passer, gotoGoal, ballAway);
		addTransition(scout, gotoGoal, returnToGoal);
		addTransition(gotoGoal, scout, atGoal);
	}

}
