package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoPlayer;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DefenseAI<T extends GameEnv> extends BaseHfsm<T> {
	
	

	public DefenseAI(FieldPlayer<T> player) throws AutomatonException {
		super(player);
		
		StudentHFSM<T> goBack = new GotoFlag<T>(player, env.getHomePos());
		StudentHFSM<T> gotoEnemy = new GotoPlayer<T>(player, false, -1);
		StudentHFSM<T> gotoBall = new GotoBall<T> (player);
		StudentHFSM<T> passer = new PasserAi<T> (player);
		StudentHFSM<T> watchBall = new LookAtBall<T> (player);
		StudentHFSM<T> scout = new Scout<T> (player);
		
		setInitialState(scout);
		
		addState(goBack);
		addState(scout);
		addState(gotoEnemy);
		addState(gotoBall);
		addState(watchBall);
		addState(passer);
		
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> hasNotScouted = new NotExpression<T>(hasScouted);
		LogicExpression<T> hasBall = new BallInDistance<T>(env, 1);
		
		LogicExpression<T> ballNear = new BallInDistance<T>(env, 30);
		LogicExpression<T> ballNotNear = new NotExpression<T>(ballNear);
		LogicExpression<T> closestToBall = new IsClosestToBall<T>(env);
		LogicExpression<T> notClosestToBall = new NotExpression<T>(closestToBall);
		
		LogicExpression<T> enemyNear = new PlayerInDistance<T>(env, false, 30);
		LogicExpression<T> enemyNotNear = new NotExpression<T>(enemyNear);
		LogicExpression<T> enemyVeryNear = new PlayerInDistance<T>(env, false, 10);
		
		LogicExpression<T> atHome = new FlagInDistance<T>(env, env.getHomePos(), 3);
		LogicExpression<T> notAtHome = new NotExpression<T>(atHome);
		
		
		LogicExpression<T> shouldGoHomeA = new AndExpression<T>(hasScouted, enemyNotNear);
		LogicExpression<T> shouldGoHomeB = new AndExpression<T>(notClosestToBall, notAtHome);
		LogicExpression<T> shouldGoHome = new AndExpression<T>(shouldGoHomeA, shouldGoHomeB);

		LogicExpression<T> shouldGotoEnemyA = new AndExpression<T>(enemyVeryNear, ballNear);
		LogicExpression<T> shouldGotoEnemyB = new AndExpression<T>(hasScouted, closestToBall);
		LogicExpression<T> shouldGotoEnemy = new AndExpression<T>(shouldGotoEnemyA, shouldGotoEnemyB);
		
		LogicExpression<T> shouldWatchBallA = new AndExpression<T>(hasScouted, atHome);
		LogicExpression<T> shouldWatchBallB = new AndExpression<T>(notClosestToBall, ballNotNear);
		LogicExpression<T> shouldWatchBall = new AndExpression<T>(shouldWatchBallA, shouldWatchBallB);

		LogicExpression<T> shouldPass = new AndExpression<T>(hasBall, hasScouted);
		
		LogicExpression<T> shouldGotoBallA = new AndExpression<T>(hasScouted, atHome);
		LogicExpression<T> shouldGotoBallB = new AndExpression<T>(closestToBall, ballNear);
		
		//Soll nur dann zum Ball gehen, wenn er nicht passen soll, sonst multiple transitions
		LogicExpression<T> shouldGotoBall = new AndExpression<T>(new AndExpression<T>(shouldGotoBallA, shouldGotoBallB), new NotExpression<T>(shouldPass));

		
		addTransition(scout, goBack, shouldGoHome);
		addTransition(gotoEnemy, goBack, shouldGoHome);
		addTransition(gotoBall, goBack, shouldGoHome);
		addTransition(watchBall, goBack, shouldGoHome);
		addTransition(passer, goBack, shouldGoHome);
		
		addTransition(goBack, scout, hasNotScouted);
		addTransition(watchBall, scout, hasNotScouted);

		addTransition(scout, gotoEnemy, shouldGotoEnemy);
		addTransition(goBack, gotoEnemy, shouldGotoEnemy);
		addTransition(watchBall, gotoEnemy, shouldGotoEnemy);

		addTransition(scout, watchBall, shouldWatchBall);
		addTransition(goBack, watchBall, shouldWatchBall);
		addTransition(passer, watchBall, shouldWatchBall);

		addTransition(watchBall, gotoBall, shouldGotoBall);
		addTransition(scout, gotoBall, shouldGotoBall);
		addTransition(gotoEnemy, gotoBall, shouldGotoBall);

		addTransition(gotoBall, passer, shouldPass);
		addTransition(gotoEnemy, passer, shouldPass);
		addTransition(watchBall, passer, shouldPass);
		addTransition(scout, passer, shouldPass);
	}
	
	public void doOutput() throws AutomatonException {
//			System.out.println(shouldGotoBall.eval(env)^shouldPass.eval(env)^shouldGotoEnemy.eval(env)^shouldGoHome.eval(env));
//			System.out.println("shouldGotoBall: " + shouldGotoBall.eval(env));
//			System.out.println("shouldPass: " + shouldPass.eval(env));
//			System.out.println("shouldGotoEnemy: " + shouldGotoEnemy.eval(env));
//			System.out.println("shouldGoHome: " + shouldGoHome.eval(env));
		
	}
	
}
