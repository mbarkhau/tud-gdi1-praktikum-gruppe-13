package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WalkToBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.InShootDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallOutDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallVisible;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtFlag;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;



public class OffensivePlayerAi<T extends GameEnv> extends StudentHFSM<T> {

	public OffensivePlayerAi(FieldPlayer<T> player) throws AutomatonException{
		
		
		StudentHFSM<T> lookAhead = new LookAhead<T>(player);  // macht nichts
		StudentHFSM<T> scout = new Scout<T>(player);  // suche den Ball
		StudentHFSM<T> turnToGoal = new TurnToGoal<T>(player); // Player dreht sich Richtung Tor
		StudentHFSM<T> runOnGoal = new RunOnGoal<T>(player);  // laufe ohne Ball auf Tor zu
		StudentHFSM<T> drawNearBall = new DrawNearBall<T>(player);  // n�here dich Ball bis H�chstentfernung erreicht
		StudentHFSM<T> walk = new WalkToBall<T>(player);  // laufe zum Ball
		StudentHFSM<T> turnToBall = new LookAtBall<T>(player); // dreh dich zum Ball
		
		StudentHFSM<T> dribble = new DribblePlayerAi<T>(player);
		
		setInitialState(lookAhead);
		
		addState(lookAhead);
		addState(scout);
		addState(turnToGoal);
		addState(runOnGoal);
		addState(turnToBall);
		addState(drawNearBall);
		
		// lookAhead "wei� nicht wo Ball ist" Scout
		addTransition(lookAhead.getName(), scout.getName(), "don't know where ball", new NotExpression<T>(new BallVisible<T>(player.getEnv())));
		
		// lookAhead "wei� wo Ball ist und ist nicht am n�hsten" TurnToGoal
		addTransition(lookAhead.getName(), turnToGoal.getName(), "not closest", new NotExpression<T>(new IsClosestToBall<T>((T) player.getEnv())));
		
		// lookAhead "ist am n�chsten zum Ball" DRIBBLE PLAYER
		addTransition(lookAhead.getName(), dribble.getName(), "is closest to Ball", new IsClosestToBall<T>((T) player.getEnv()));
		
		// Scout "Ball gefunden" lookAhead
		addTransition(scout.getName(), lookAhead.getName(), "found ball", new BallVisible<T>((T) player.getEnv()));
				
		// turnToGoal "ist in Tor-Richtung" runOnGoal
		addTransition(turnToGoal.getName(), runOnGoal.getName(), "is in Goal Direction", new LookingAtFlag<T>((T) player.getEnv(), T_G_C));
		
		// turnToGoal "verliert ball aus Augen" lookAhead
		addTransition(turnToGoal.getName(), lookAhead.getName(), "lost ball off eyes", new NotExpression<T>(new IsClosestToBall<T>((T) player.getEnv())) );
		
		// runOnGoal "ist nicht in Tor-Richtung" turnToGoal
		NotExpression<T> isNotInGoalDirection = new NotExpression<T>(new LookingAtFlag<T>((T) player.getEnv(), T_G_C));
		addTransition(runOnGoal.getName(), turnToGoal.getName(), "is not in goal direction", isNotInGoalDirection);
		
		// runOnGoal "verliert ball aus Augen" lookAhead
		addTransition(runOnGoal.getName(), lookAhead.getName(), "lost ball off eyes", new NotExpression<T>(new IsClosestToBall<T>((T) player.getEnv())));
		
		// runOnGoal "ist nahe genug an TOr" lookAhead
		addTransition(runOnGoal.getName(), lookAhead.getName(), "is close enough to goal", new InShootDistance<T>((T) player.getEnv()));
		
		// runOnGoal "zu weit weg von Ball" turnToBall
		addTransition(runOnGoal.getName(), turnToBall.getName(), "to far from ball", new BallOutDistance<T>((T) player.getEnv()));
		
		// turnToBall "schaut Richtung Ball" drawNearBall
		addTransition(turnToBall.getName(), drawNearBall.getName(), "is in Ball direction", new LookingAtBall<T>((T) player.getEnv()));
		
		// drawNearBall "nicht zu weit weg von Ball" lookAhead
		addTransition(drawNearBall.getName(), lookAhead.getName(), "not to far from ball", new NotExpression<T>(new BallOutDistance<T>((T) player.getEnv())));
		
		
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
