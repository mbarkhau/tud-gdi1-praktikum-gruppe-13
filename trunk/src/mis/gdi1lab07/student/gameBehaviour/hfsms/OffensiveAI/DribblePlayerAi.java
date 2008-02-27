package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.PasserAi;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Shoot;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DribblePlayerAi<T extends GameEnv> extends BaseHfsm<T> {

	public DribblePlayerAi(FieldPlayer<T> player) throws AutomatonException{
		super(player);
		
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> gotoBall = new GotoBall<T>(player, POWER_SPRINT);
		StudentHFSM<T> dribble = new DribbleOnGoal<T>(player);
		StudentHFSM<T> shoot = new Shoot<T>(player);
		StudentHFSM<T> passer = new PasserAi<T>(player);
		
		addState(scout);
		addState(gotoBall);
		addState(dribble);
		addState(shoot);
		addState(passer);

		setInitialState(scout);
		
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> hasNotScouted = new NotExpression<T>(hasScouted);
				
		LogicExpression<T> inShootDist = new FlagInDistance<T>(env, T_G_C, 25);
		LogicExpression<T> notInShootDist = new NotExpression<T>(inShootDist);

		LogicExpression<T> atBall = new BallInDistance<T>(env, 1);
		LogicExpression<T> notAtBall = new NotExpression<T>(atBall);

		LogicExpression<T> enemyNear = new PlayerInDistance<T>(player.getEnv(), false, 6);
		LogicExpression<T> enemyNotNear = new NotExpression<T>(enemyNear);
		
		LogicExpression<T> shouldDribbleA = new AndExpression<T>(hasScouted, enemyNotNear);
		LogicExpression<T> shouldDribbleB = new AndExpression<T>(shouldDribbleA, atBall);
		LogicExpression<T> shouldDribble = new AndExpression<T>(shouldDribbleB, notInShootDist);
		
		LogicExpression<T> shouldGotoBall = new AndExpression<T>(hasScouted, notAtBall);
		
		LogicExpression<T> shouldShoot = new AndExpression<T>(inShootDist, atBall);

		LogicExpression<T> shouldPassA = new AndExpression<T>(enemyNear, atBall);
		LogicExpression<T> shouldPassB = new AndExpression<T>(shouldPassA, notInShootDist);
		LogicExpression<T> shouldPass = new AndExpression<T>(shouldPassB, hasScouted);
		
		
		addTransition(gotoBall, scout, hasNotScouted);
		addTransition(dribble, scout, hasNotScouted);
		
		addTransition(scout, gotoBall, shouldGotoBall);
		addTransition(shoot, gotoBall, shouldGotoBall);
		addTransition(dribble, gotoBall, shouldGotoBall);
		addTransition(passer, gotoBall, shouldGotoBall);
		
		addTransition(scout, dribble, shouldDribble);
		addTransition(gotoBall, dribble, shouldDribble);
		addTransition(shoot, dribble, shouldDribble);
		addTransition(passer, dribble, shouldDribble);

		addTransition(scout, shoot, shouldShoot);
		addTransition(gotoBall, shoot, shouldShoot);
		addTransition(dribble, shoot, shouldShoot);
		addTransition(passer, shoot, shouldShoot);
		
		addTransition(scout, passer, shouldPass);
		addTransition(gotoBall, passer, shouldPass);
		addTransition(dribble, passer, shouldPass);
		addTransition(shoot, passer, shouldPass);
		
		
	}

}
