package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoPlayer;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Shoot;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallInFlagRange;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.PlayerInFlagRange;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DefenseAI<T extends GameEnv> extends BaseHfsm<T> {
	
	

	public DefenseAI(FieldPlayer<T> player) throws AutomatonException {
		super(player);
		
		StudentHFSM<T> goHome = new GotoFlag<T>(player, env.getHomePos());
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> passer = new PasserAi<T> (player);
		StudentHFSM<T> passee = new PasseeAi<T> (player);
		StudentHFSM<T> clear = new Shoot<T> (player);
		StudentHFSM<T> gotoEnemy = new GotoPlayer<T> (player, false, -1);
		
		setInitialState(scout);
		
		addState(goHome);
		addState(scout);
		addState(passer);
		addState(passee);
		addState(clear);
		addState(gotoEnemy);
		
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> hasNotScouted = new NotExpression<T>(hasScouted);
		
		LogicExpression<T> hasBall = new BallInDistance<T>(env, 1);
		LogicExpression<T> notHasBall = new NotExpression<T>(hasBall);
				
		LogicExpression<T> closestToBall = new IsClosestToBall<T>(env);
		LogicExpression<T> notClosestToBall = new NotExpression<T>(closestToBall);
		
		LogicExpression<T> ballInDefenseRange = new BallInFlagRange<T>(env, 20, env.getHomePos());
		LogicExpression<T> ballNotInDefenseRange = new NotExpression<T>(ballInDefenseRange);
		
		LogicExpression<T> enemyInDefenseRange = new PlayerInFlagRange<T>(env, -1, env.getHomePos(), 17);
		LogicExpression<T> enemyNotInDefenseRange = new NotExpression<T>(enemyInDefenseRange);
		LogicExpression<T> enemyNearMe = new PlayerInDistance<T>(env, false, 7);
		
		LogicExpression<T> atHome = new FlagInDistance<T>(env, env.getHomePos(), 4);
		LogicExpression<T> notAtHome = new NotExpression<T>(atHome);

		
		LogicExpression<T> shouldClearA = new AndExpression<T>(enemyNearMe, hasScouted);
		LogicExpression<T> shouldClear = new AndExpression<T>(shouldClearA, hasBall);
		
		LogicExpression<T> shouldGoHomeA = new AndExpression<T>(ballNotInDefenseRange, notAtHome);
		LogicExpression<T> shouldGoHome = new AndExpression<T>(shouldGoHomeA, notClosestToBall);
		LogicExpression<T> shouldNotGoHome = new NotExpression<T>(shouldGoHome);
		
		LogicExpression<T> passerGoHomeA = new AndExpression<T>(shouldGoHome, notHasBall);
		LogicExpression<T> passerGoHomeB = new AndExpression<T>(passerGoHomeA, notClosestToBall);
		LogicExpression<T> passerGoHome = new AndExpression<T>(passerGoHomeB, hasScouted);
		
		LogicExpression<T> clearFailed = new AndExpression<T>(notHasBall, shouldNotGoHome); 
		
		LogicExpression<T> shouldPassA = new OrExpression<T>(ballInDefenseRange, closestToBall);
		LogicExpression<T> shouldPass = new AndExpression<T>(shouldPassA, hasScouted); 
		LogicExpression<T> shouldNotPass = new NotExpression<T>(shouldPass);

		LogicExpression<T> acceptPassA = new AndExpression<T>(ballNotInDefenseRange, hasScouted);
		LogicExpression<T> acceptPassB = new AndExpression<T>(acceptPassA, atHome);
		LogicExpression<T> acceptPass = new AndExpression<T>(acceptPassB, notClosestToBall);
		
		LogicExpression<T> shouldGuardA =  new AndExpression<T>(shouldNotPass, enemyNotInDefenseRange);
		LogicExpression<T> shouldGuard =  new AndExpression<T>(shouldGuardA, hasScouted);
		
		//TODO: maybe stop guard, if we have reached enemy
		LogicExpression<T> stopGuard = new OrExpression<T>(hasNotScouted, enemyNotInDefenseRange);
		
		addTransition(passer, clear, shouldClear);
		
		addTransition(clear, goHome, shouldGoHome);
		addTransition(scout, goHome, shouldGoHome);

		addTransition(passer, goHome, passerGoHome);
		
		addTransition(clear, passer, clearFailed);
		
		addTransition(scout, passer, shouldPass);
		addTransition(passee, passer, shouldPass);
//		addTransition(gotoEnemy, passer, shouldPass);

		addTransition(passer, scout, hasNotScouted);
		addTransition(passee, scout, hasNotScouted);
		
		addTransition(scout, passee, acceptPass);
		
		addTransition(goHome, scout, atHome);
		
		addTransition(gotoEnemy, scout, stopGuard);
		
		addTransition(passee, gotoEnemy, shouldGuard);
		
	}
	
}
