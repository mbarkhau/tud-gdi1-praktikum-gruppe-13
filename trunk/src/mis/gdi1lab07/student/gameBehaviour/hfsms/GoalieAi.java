package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI.Shoot;
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

	//TODO: Erkennung, ob im Strafraum oder nicht!
	
	
	
	public GoalieAi(FieldPlayer<T> player) throws AutomatonException {
		super(player);
		
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> gotoGoal = new GotoFlag<T>(player, O_G_C);
		StudentHFSM<T> watchBall = new LookAtBall<T>(player);		
		watchBall.setName("watchBall");
		StudentHFSM<T> grabBall = new GrabBall<T>(player);
		grabBall.setName("grabBall");
		StudentHFSM<T> passer = new PassAi<T>(player);
		StudentHFSM<T> kick = new Shoot<T>(player);
		kick.setName("kick");
		GotoBall<T> gotoBall = new GotoBall<T>(player);
		gotoBall.setName("gotoBall");
		gotoBall.setPower(120);

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
		LogicExpression<T> penaltyFlagsInView = new OrExpression<T> (new OrExpression<T>( new LookingAtFlag<T>(env, O_P_C), new LookingAtFlag<T>(env, O_P_L)), new LookingAtFlag<T>(env, O_P_R));
		LogicExpression<T> penaltyFlagsNotInView = new NotExpression<T>(penaltyFlagsInView);
		
		LogicExpression<T> isInPenaltyArea1 = new AndExpression<T>(goalInView, penaltyFlagsNotInView);
		LogicExpression<T> isInPenaltyArea2 = new AndExpression<T>(goalNotInView, penaltyFlagsInView);
		LogicExpression<T> isInPenaltyArea = new OrExpression<T>(isInPenaltyArea1, isInPenaltyArea2);
		
		LogicExpression<T> ballInGrabDist = new BallInDistance<T>(env, 2);
		
		//Goalie darf Ball nehmen, wenn er im Strafraum ist und der Ball weniger als 2 Meter entfernt ist
//		LogicExpression<T> readyToGrab = new AndExpression<T>(isInPenaltyArea, ballInGrabDist);
		LogicExpression<T> readyToGrab = ballInGrabDist;
		
		//Feind attackiert, sobald er und der Ball weniger als 20 Meter vom Tor entfernt sind
		LogicExpression<T> ballInDangerDist = new BallInDistance<T>(env, 20);
		LogicExpression<T> playerInDangerDist = new PlayerInDistance<T>(env, false, 20);
//		LogicExpression<T> enemyAttacks = new AndExpression<T>(ballInDangerDist, playerInDangerDist);
		
		//Neue enemyAttack-Expression: Wenn der Ball näher ist als 5 Meter, soll er auf jeden Fall den Ball nehmen.
		LogicExpression<T> enemyAttacks = new OrExpression<T> (new AndExpression<T>(ballInDangerDist, playerInDangerDist), new BallInDistance<T>(env, 10));
		
		
		//Goalie hat Ball gefangen, er ist somit weniger als 1 Meter entfernt
		LogicExpression<T> grabbedBall = new BallInDistance<T>(env, 1);
		
		//Wenn der Ball mehr als 40 Meter entfernt ist
		LogicExpression<T> ballAway = new NotExpression<T>(new BallInDistance<T>(env, 40));
		LogicExpression<T> returnToGoal = new AndExpression<T>(ballAway, notAtGoal);
		
		//Leave Ball-Bedingung: Wenn der Ball weg ist oder der Goalie näher als 35 Meter an der Zentrumsflagge ist
		LogicExpression<T> leaveBall = new OrExpression<T> (ballAway, new FlagInDistance<T>(env, C, 35));
		
		//Wenn der Ball mehr als 10 Meter entfernt ist (nach wegkicken)
		LogicExpression<T> ballKickedAway = new BallInDistance<T> (env, 10);
		
		addTransition(scout, watchBall, atGoal);
		addTransition(watchBall, scout, hasNotScouted);
		addTransition(watchBall, grabBall, readyToGrab);
		addTransition(watchBall, gotoBall, enemyAttacks);
		addTransition(gotoBall, grabBall, readyToGrab);
//		addTransition(grabBall, passer, grabbedBall);
		addTransition(grabBall, kick, grabbedBall);
//		addTransition(passer, gotoGoal, ballAway);
		addTransition(kick, gotoGoal, ballKickedAway);
		addTransition(gotoBall, gotoGoal, leaveBall);
		addTransition(grabBall, gotoGoal, ballAway);
		addTransition(scout, gotoGoal, returnToGoal);
		addTransition(gotoGoal, scout, atGoal);
	}
	
	public void doOutput(){
		System.out.println("Goalie-Status: " + this.getCurrentState().toString());
		
		
		
		
	}
	
}
