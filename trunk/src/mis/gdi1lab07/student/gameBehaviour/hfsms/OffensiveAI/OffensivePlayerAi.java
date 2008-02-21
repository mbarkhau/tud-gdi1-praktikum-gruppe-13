package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.Scout;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WalkToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAknowledgement;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInBallDirection;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInGoalDirection;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInShootDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.SeeBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.TooFarFromBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;



public class OffensivePlayerAi<T extends GameEnv> extends StudentHFSM<T> {

	public OffensivePlayerAi(FieldPlayer player) throws AutomatonException{
		
		
		StudentHFSM<T> lookAhead = new LookAhead<T>(player);  // macht nichts
		StudentHFSM<T> scout = new Scout<T>(player);  // suche den Ball
		StudentHFSM<T> turnToGoal = new TurnToGoal<T>(player); // Player dreht sich Richtung Tor
		StudentHFSM<T> runOnGoal = new RunOnGoal<T>(player);  // laufe ohne Ball auf Tor zu
		StudentHFSM<T> drawNearBall = new DrawNearBall<T>(player);  // nähere dich Ball bis Höchstentfernung erreicht
		StudentHFSM<T> walk = new WalkToBall<T>(player);  // laufe zum Ball
		StudentHFSM<T> turnToBall = new TurnToBall<T>(player); // dreh dich zum Ball
		
		setInitialState(lookAhead);
		
		addState(lookAhead);
		addState(scout);
		addState(turnToGoal);
		addState(runOnGoal);
		addState(turnToBall);
		
		// lookAhead "weiß nicht wo Ball ist" Scout
		NotExpression<T> seeBallNOT = new NotExpression(new SeeBall((T) player.getEnv()));
		addTransition(lookAhead.getName(), scout.getName(), "don't know where ball", seeBallNOT);
		
		// lookAhead "weiß wo Ball ist und ist nicht am nähsten" TurnToGoal
		NotExpression<T> isClosestToBallNOT = new NotExpression(new IsClosestToBall<T>((T) player.getEnv()));
		addTransition(lookAhead.getName(), turnToGoal.getName(), "not closest", isClosestToBallNOT);
		
		// TODO lookAhead "ist am nächsten zum Ball" DRIBBLE PLAYER
		
		
		// Scout "Ball gefunden" lookAhead
		addTransition(scout.getName(), lookAhead.getName(), "found ball", new SeeBall<T>((T) player.getEnv()));
				
		// turnToGoal "ist in Tor-Richtung" runOnGoal
		addTransition(turnToGoal.getName(), runOnGoal.getName(), "is in Goal Direction", new IsInGoalDirection<T>((T) player.getEnv()));
		
		// turnToGoal "verliert ball aus Augen" lookAhead
		addTransition(turnToGoal.getName(), lookAhead.getName(), "lost ball off eyes", seeBallNOT );
		
		// runOnGoal "ist nicht in Tor-Richtung" turnToGoal
		NotExpression isNotInGoalDirection = new NotExpression(new IsInGoalDirection<T>((T) player.getEnv()));
		addTransition(runOnGoal.getName(), turnToGoal.getName(), "is not in goal direction", isNotInGoalDirection);
		
		// runOnGoal "verliert ball aus Augen" lookAhead
		addTransition(runOnGoal.getName(), lookAhead.getName(), "lost ball off eyes", seeBallNOT );
		
		// runOnGoal "ist nahe genug an TOr" lookAhead
		addTransition(runOnGoal.getName(), lookAhead.getName(), "is close enough to goal", new IsInShootDistance<T>((T) player.getEnv()));
		
		// runOnGoal "zu weit weg von Ball" turnToBall
		addTransition(runOnGoal.getName(), turnToBall.getName(), "to far from ball", new TooFarFromBall<T>((T) player.getEnv()));
		
		// turnToBall "schaut Richtung Ball" drawNearBall
		addTransition(turnToBall.getName(), drawNearBall.getName(), "is in Ball direction", new IsInBallDirection<T>((T) player.getEnv()));
		
		// drawNearBall "nicht zu weit weg von Ball" lookAhead
		NotExpression notTooFarFromBall = new NotExpression(new TooFarFromBall<T>((T) player.getEnv()));
		addTransition(drawNearBall.getName(), lookAhead.getName(), "not to far from ball", notTooFarFromBall);
		
		
	}
}



//public class PasseeAi<T extends GameEnv> extends StudentHFSM<T> {

//	public PasseeAi(FieldPlayer player) throws AutomatonException {

//		StudentHFSM<T> walk = new WalkToBall<T>(player);
//		StudentHFSM<T> watchBall = new WatchBall<T>(player);
//		StudentHFSM<T> acceptPass = new AcceptPassFrom<T>(player);
//		StudentHFSM<T> wait = new Wait<T>(player);
//
//		setInitialState(watchBall);
//
//		addState(watchBall);
//		addState(walk);
//		addState(acceptPass);
//		addState(wait);
//
//		addTransition(watchBall.getName(), acceptPass.getName(), "accept pass",
//				new HasHeardRequest<T>((T) player.getEnv()));
//		addTransition(acceptPass.getName(), walk.getName(), "goto ball",
//				new HasHeardAknowledgement<T>((T) player.getEnv()));
//		addTransition(walk.getName(), wait.getName(), "got ball",
//				new IsAtBall<T>((T) player.getEnv()));
//	}
//}
