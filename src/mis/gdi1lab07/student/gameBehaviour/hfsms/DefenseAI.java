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
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoPlayer;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallInFlagRange;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAck;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.PlayerInFlagRange;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DefenseAI<T extends GameEnv> extends BaseHfsm<T> {
	
	

	public DefenseAI(FieldPlayer<T> player) throws AutomatonException {
		super(player);
		
		StudentHFSM<T> goBack = new GotoFlag<T>(player, env.getHomePos());
		StudentHFSM<T> gotoEnemy = new GotoPlayer<T>(player, false, -1);
		StudentHFSM<T> gotoBall = new GotoBall<T> (player, POWER_SPRINT);
		StudentHFSM<T> pass = new PassAi<T> (player);
		StudentHFSM<T> scout = new Scout<T> (player);
		
		setInitialState(scout);
		
		addState(goBack);
		addState(scout);
		addState(gotoEnemy);
		addState(gotoBall);
		addState(pass);
		
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> hasNotScouted = new NotExpression<T>(hasScouted);
		LogicExpression<T> hasBall = new BallInDistance<T>(env, 1);
		LogicExpression<T> hasNotBall = new NotExpression<T>(hasBall);
		
		LogicExpression<T> ballNear = new BallInDistance<T>(env, 30);
		LogicExpression<T> ballNotNear = new NotExpression<T>(ballNear);
		
		LogicExpression<T> closestToBall = new IsClosestToBall<T>(env);
		LogicExpression<T> notClosestToBall = new NotExpression<T>(closestToBall);
		
		LogicExpression<T> ballInDefenseRange = new BallInFlagRange<T>(env, 16, env.getHomePos());
		LogicExpression<T> ballNotInDefenseRange = new NotExpression<T>(ballInDefenseRange);
		
		LogicExpression<T> enemyNear = new PlayerInFlagRange<T>(env, -1, env.getHomePos(), 10);
		LogicExpression<T> enemyNotNear = new NotExpression<T>(enemyNear);
		
		LogicExpression<T> atHome = new FlagInDistance<T>(env, env.getHomePos(), 3);
		LogicExpression<T> notAtHome = new NotExpression<T>(atHome);
		
		

		LogicExpression<T> hasHeardRequest = new HasHeardRequest<T>(env);
		LogicExpression<T> hasHeardAck = new HasHeardAck<T>(env);
		LogicExpression<T> hasHeardMsg = new OrExpression<T>(hasHeardRequest, hasHeardAck);
		
		LogicExpression<T> isPassA = new AndExpression<T>(hasHeardMsg, atHome);
		LogicExpression<T> isPassB = new OrExpression<T>(isPassA, hasBall);
		LogicExpression<T> isPass = new AndExpression<T>(isPassB, hasScouted);
		
		LogicExpression<T> shouldGotoBallA = new AndExpression<T>(hasScouted, ballInDefenseRange);
		LogicExpression<T> shouldGotoBallB = new AndExpression<T>(shouldGotoBallA, hasNotBall);
		LogicExpression<T> shouldGotoBall = new OrExpression<T>(shouldGotoBallB, closestToBall);
		
		LogicExpression<T> shouldNotGotoBall = new NotExpression<T>(shouldGotoBall);

		LogicExpression<T> shouldGoHomeA = new AndExpression<T>(hasScouted, enemyNotNear);
		LogicExpression<T> shouldGoHomeB = new AndExpression<T>(shouldNotGotoBall, notAtHome);
		LogicExpression<T> shouldGoHome = new AndExpression<T>(shouldGoHomeA, shouldGoHomeB);
		
		LogicExpression<T> shouldGotoEnemyA = new AndExpression<T>(enemyNear, notClosestToBall);
		LogicExpression<T> shouldGotoEnemyB = new AndExpression<T>(shouldGotoEnemyA, shouldNotGotoBall);
		LogicExpression<T> shouldGotoEnemy = new AndExpression<T>(shouldGotoEnemyB, hasScouted);
		
		addTransition(scout, goBack, shouldGoHome);
		addTransition(gotoEnemy, goBack, shouldGoHome);
		addTransition(gotoBall, goBack, shouldGoHome);
		addTransition(pass, goBack, shouldGoHome);
		
		addTransition(goBack, scout, hasNotScouted);
		addTransition(gotoEnemy, scout, hasNotScouted);
		addTransition(gotoBall, scout, hasNotScouted);

		addTransition(scout, gotoEnemy, shouldGotoEnemy);
		addTransition(goBack, gotoEnemy, shouldGotoEnemy);

		addTransition(scout, gotoBall, shouldGotoBall);
		addTransition(gotoEnemy, gotoBall, shouldGotoBall);
		addTransition(pass, gotoBall, shouldGotoBall);

		addTransition(gotoBall, pass, isPass);
		addTransition(gotoEnemy, pass, isPass);
		addTransition(scout, pass, isPass);
	}
	
	public void doOutput() throws AutomatonException {
//			System.out.println(shouldGotoBall.eval(env)^shouldPass.eval(env)^shouldGotoEnemy.eval(env)^shouldGoHome.eval(env));
//			System.out.println("shouldGotoBall: " + shouldGotoBall.eval(env));
//			System.out.println("shouldPass: " + shouldPass.eval(env));
//			System.out.println("shouldGotoEnemy: " + shouldGotoEnemy.eval(env));
//			System.out.println("shouldGoHome: " + shouldGoHome.eval(env));
		
	}
	
}
