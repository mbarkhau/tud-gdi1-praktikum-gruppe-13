package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInGoalDirection;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInShootDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.SeeBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.TooFarFromBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;


public class OffensivePlayerAi<T extends GameEnv> extends StudentHFSM<T> {

	public OffensivePlayerAi(FieldPlayer<T> player) throws AutomatonException {  
		
		StudentHFSM<T> lookAhead = new LookAhead<T>(player);  // macht nichts
		StudentHFSM<T> scout = new Scout<T>(player);  // suche den Ball
		StudentHFSM<T> turnToGoal = new LookAtFlag<T>(player, FlagConstants.T_G_C); // Player dreht sich Richtung Tor
		StudentHFSM<T> runOnGoal = new RunOnGoal<T>(player);  // laufe ohne Ball auf Tor zu
		StudentHFSM<T> drawNearBall = new DrawNearBall<T>(player);  // n�here dich Ball bis H�chstentfernung erreicht
		StudentHFSM<T> turnToBall = new TurnToBall<T>(player); // dreh dich zum Ball
		StudentHFSM<T> goBack = new GotoFlag<T>(player, player.getEnv().getHomePos());
		
		setInitialState(lookAhead);
		
		addState(lookAhead);
		addState(scout);
		addState(turnToGoal);
		addState(runOnGoal);
		addState(turnToBall);
		addState(drawNearBall);
		addState(goBack);
		
		
		SeeBall<T> seeBall = new SeeBall<T>(player.getEnv());
		NotExpression<T> notSeeBall = new NotExpression<T>(seeBall);
		
		LookingAtBall<T> lookingAtBall = new LookingAtBall<T>((T) player.getEnv());
		NotExpression<T> notLookingAtBall = new NotExpression<T>(lookingAtBall);
		
		IsInGoalDirection<T> inGoalDir = new IsInGoalDirection<T>(player.getEnv());
		NotExpression<T> notInGoalDir = new NotExpression<T>(inGoalDir);
		
		TooFarFromBall<T> tooFarFromBall = new TooFarFromBall<T>(player.getEnv());
		NotExpression<T> notTooFarFromBall = new NotExpression<T>(tooFarFromBall);
	
		IsInShootDistance<T> inShootDist = new IsInShootDistance<T>((T) player.getEnv());
		NotExpression<T> notInShootDist = new NotExpression<T>(inShootDist);
		
		OrExpression<T> notInGoalDirOrTooFarFromBall = new OrExpression<T>(notInGoalDir, tooFarFromBall);
		AndExpression<T> seeBallAndNotTooFarFromBall = new AndExpression<T>(seeBall, notTooFarFromBall);
		AndExpression<T> seeBallAndNotTooFarFromBallAndInShootDist = new AndExpression<T>(seeBallAndNotTooFarFromBall, inShootDist);
		OrExpression<T> notLookingAtBallOrNotTooFarFromBall = new OrExpression<T>(notLookingAtBall, notTooFarFromBall);
		AndExpression<T> seeBallAndTooFarFromBall = new AndExpression<T>(seeBall, notTooFarFromBall);
		AndExpression<T> seeBallAndInGoalDir = new AndExpression<T>(seeBall, inGoalDir);
		
		
		addTransition(lookAhead, scout, notSeeBall);
		
		// lookAhead "weiss wo Ball ist, zu weit vom Ball und nicht an Startposition" goBack
		addTransition(lookAhead, goBack, new AndExpression<T>(
													seeBallAndTooFarFromBall,
													new NotExpression<T>(new FlagInDistance<T>(player.getEnv(), player.getEnv().getHomePos(), 25))));
		// lookAhead "weiss wo Ball ist, zu weit vom Ball und an Startposition" drawNearBall
		addTransition(lookAhead, turnToBall, new AndExpression<T>(
							seeBallAndTooFarFromBall,
							new FlagInDistance<T>(player.getEnv(), player.getEnv().getHomePos(), 10)));
		addTransition(lookAhead, turnToGoal, seeBallAndNotTooFarFromBall);
		
		
		addTransition(scout, lookAhead, seeBall);
		
		
		addTransition(turnToGoal, runOnGoal, seeBallAndInGoalDir);
		addTransition(turnToGoal, lookAhead, notSeeBall);
		
		
		// runOnGoal "sieht Ball und (ist nicht in Tor-Richtung oder zu weit weg von Ball" lookAhead
		addTransition(runOnGoal, lookAhead, new AndExpression<T>(seeBall, notInGoalDirOrTooFarFromBall));
		addTransition(runOnGoal, lookAhead, seeBallAndNotTooFarFromBallAndInShootDist);
		addTransition(runOnGoal, lookAhead, notSeeBall);
		
		
		addTransition(turnToBall, drawNearBall, notLookingAtBall);
		addTransition(turnToBall, lookAhead, notSeeBall);

		
		addTransition(drawNearBall, lookAhead, notSeeBall);
		addTransition(drawNearBall, lookAhead, new AndExpression<T>(seeBall, notLookingAtBallOrNotTooFarFromBall));
		
		
		// goBack "ist zurueck" lookAhead
		addTransition(goBack, lookAhead, new FlagInDistance<T>(player.getEnv(), player.getEnv().getHomePos(), 8));
		
	}
}