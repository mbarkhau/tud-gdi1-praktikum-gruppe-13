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
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Shoot;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallVisible;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtFlag;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

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
		StudentHFSM<T> passer = new PasserAi<T>(player);
		passer.setName("Passer");
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
		
		
		//Feind attackiert, sobald er und der Ball weniger als 20 Meter vom Tor entfernt sind
		LogicExpression<T> ballInDangerDist = new BallInDistance<T>(env, 20);
		LogicExpression<T> playerInDangerDist = new PlayerInDistance<T>(env, false, 20);
//		LogicExpression<T> enemyAttacks = new AndExpression<T>(ballInDangerDist, playerInDangerDist);
		
		//Neue enemyAttack-Expression: Wenn der Ball n�her ist als 5 Meter, soll er auf jeden Fall den Ball nehmen.
		LogicExpression<T> enemyAttacks = new OrExpression<T> (new AndExpression<T>(ballInDangerDist, playerInDangerDist), new BallInDistance<T>(env, 10));
		
		
		//Goalie hat Ball gefangen, er ist somit weniger als 1 Meter entfernt
		LogicExpression<T> grabbedBall = new BallInDistance<T>(env, 1);
		
		//Wenn der Ball mehr als 40 Meter entfernt ist
		LogicExpression<T> ballAway = new NotExpression<T>(new BallInDistance<T>(env, 40));
		LogicExpression<T> returnToGoal = new AndExpression<T>(ballAway, notAtGoal);
		
			
		//Wenn der Ball mehr als 10 Meter entfernt ist (nach wegkicken)
		LogicExpression<T> ballKickedAway = new NotExpression<T> (new BallInDistance<T> (env, 10));
		
		//Wenn er nicht gescouted hat und kein Gegner angreift, soll er scouten
		LogicExpression<T> doScout = new AndExpression<T>(hasNotScouted, new NotExpression<T>(enemyAttacks));
		
		
		//Expression, wenn eine der 3 Strafraumflaggen weniger als 
		LogicExpression<T> FlagRTooNear = new FlagInDistance<T>(env, O_P_R, 15);
		LogicExpression<T> FlagLTooNear = new FlagInDistance<T>(env, O_P_L, 15);
		LogicExpression<T> FlagCTooNear = new FlagInDistance<T>(env, O_P_C, 5);
		
		LogicExpression<T> onePenaltyFlagIsTooNear = new OrExpression<T>(new OrExpression<T>(FlagRTooNear, FlagLTooNear), FlagCTooNear);
		
		//Leave Ball-Bedingung: Wenn der Ball weg ist oder eine Strafraumflagge zu nahe kommt
		LogicExpression<T> leaveBall = new OrExpression<T> (ballAway, onePenaltyFlagIsTooNear);

		//Goalie darf Ball nehmen, wenn er im Strafraum ist und der Ball weniger als 2 Meter entfernt ist
//		LogicExpression<T> readyToGrab = new AndExpression<T>(isInPenaltyArea, ballInGrabDist);
		LogicExpression<T> readyToGrab = new AndExpression<T>(ballInGrabDist, new NotExpression<T>(onePenaltyFlagIsTooNear));
		
		//Soll nur dann zum Ball gehen, wenn er ihn nicht greifen kann
		LogicExpression<T> doGotoBall = new AndExpression<T>(enemyAttacks, new NotExpression<T>(readyToGrab));
		
		
		addTransition(scout, watchBall, atGoal);
		addTransition(watchBall, scout, doScout);
		addTransition(watchBall, grabBall, readyToGrab);
		addTransition(watchBall, gotoBall, doGotoBall);
		addTransition(gotoBall, grabBall, readyToGrab);
		addTransition(grabBall, passer, grabbedBall);
//		addTransition(grabBall, kick, grabbedBall);
		addTransition(passer, gotoGoal, ballKickedAway);
//		addTransition(kick, gotoGoal, ballKickedAway);
		addTransition(gotoBall, gotoGoal, leaveBall);
		addTransition(grabBall, gotoGoal, leaveBall);
		addTransition(scout, gotoGoal, returnToGoal);
		addTransition(gotoGoal, scout, atGoal);
	}
	
	public void doOutput(){
		if (Utils.getDebugLevel() > Utils.DBG_STATES)
			System.out.println("Goalie-Status: " + this.getCurrentState().toString());
		
	}
	
}
