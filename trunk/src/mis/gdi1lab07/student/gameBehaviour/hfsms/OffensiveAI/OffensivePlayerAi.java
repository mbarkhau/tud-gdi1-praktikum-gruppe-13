package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.Scout;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WalkToBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAknowledgement;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallVisible;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtBall;
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
		StudentHFSM<T> walk = new GotoBall<T>(player);  // laufe zum Ball
		StudentHFSM<T> turnToBall = new TurnToBall<T>(player); // dreh dich zum Ball
		//StudentHFSM<T> dribble = new DribblePlayerAi<T>(player);
		
		setInitialState(lookAhead);
		
		//addState(dribble);
		addState(lookAhead);
		addState(scout);
		addState(turnToGoal);
		addState(runOnGoal);
		addState(turnToBall);
		addState(drawNearBall);
		
		// lookAhead "weiß nicht wo Ball ist" Scout
		addTransition(lookAhead.getName(), scout.getName(), "don't know where ball", new NotExpression(new BallVisible((T) player.getEnv())));
		
		// lookAhead "weiß wo Ball ist und ist nicht am nähsten" TurnToGoal
		addTransition(lookAhead.getName(), turnToGoal.getName(), "not closest", (new AndExpression(
									new BallVisible((T) player.getEnv()),new NotExpression(new IsClosestToBall<T>((T) player.getEnv())))));
		
		// lookAhead "ist am nächsten zum Ball" DRIBBLE PLAYER
		//addTransition(lookAhead.getName(), dribble.getName(), "is closest to Ball", (new AndExpression(
			//						new SeeBall((T) player.getEnv()),new IsClosestToBall<T>((T) player.getEnv()))));
		
		// Scout "Ball gefunden" lookAhead
		addTransition(scout.getName(), lookAhead.getName(), "found ball", new BallVisible<T>((T) player.getEnv()));
				
		// turnToGoal "(nicht in Schussdistanz, sieht Ball) und: in Tor-Richtung " runOnGoal
		addTransition(turnToGoal.getName(), runOnGoal.getName(), "is in Goal Direction", new AndExpression(
				new NotExpression<T>(new IsInShootDistance<T>((T) player.getEnv())),
				new AndExpression(
						new SeeBall<T>((T) player.getEnv()), new IsInGoalDirection<T>((T) player.getEnv()))));
		
		// turnToGoal "verliert ball aus Augen" lookAhead
		addTransition(turnToGoal.getName(), lookAhead.getName(), "lost ball off eyes", new NotExpression(new SeeBall<T>((T) player.getEnv())) );
		
		// runOnGoal "ist nicht in Tor-Richtung" turnToGoal
		addTransition(runOnGoal.getName(), turnToGoal.getName(), "is not in goal direction", new AndExpression<T>
								(new SeeBall<T>((T)player.getEnv()),new NotExpression(new IsInGoalDirection<T>((T) player.getEnv()))));
		
		// runOnGoal "verliert ball aus Augen" lookAhead
		addTransition(runOnGoal.getName(), lookAhead.getName(), "lost ball off eyes", new NotExpression(new SeeBall<T>((T) player.getEnv())));
		
		// runOnGoal "ist nahe genug an Tor" lookAhead
		addTransition(runOnGoal.getName(), lookAhead.getName(), "is close enough to goal", new IsInShootDistance<T>((T) player.getEnv()));
		
		// runOnGoal "zu weit weg von Ball" turnToBall
		addTransition(runOnGoal.getName(), turnToBall.getName(), "to far from ball", new TooFarFromBall<T>((T) player.getEnv()));
		
		// turnToBall "schaut Richtung Ball" drawNearBall
		addTransition(turnToBall.getName(), drawNearBall.getName(), "is in Ball direction", new LookingAtBall<T>((T) player.getEnv()));
		
		// drawNearBall "verliert Ball aus Augen" lookAhead
		addTransition(drawNearBall.getName(), lookAhead.getName(), "lost ball off eyes", new NotExpression<T>(new SeeBall<T>((T) player.getEnv())));
		
		// drawNearBall "nicht mehr zu weit weg von Ball" lookAhead
		addTransition(drawNearBall.getName(), lookAhead.getName(), "not to far from ball", new NotExpression(new TooFarFromBall<T>((T) player.getEnv())));
		
		
	}
}



