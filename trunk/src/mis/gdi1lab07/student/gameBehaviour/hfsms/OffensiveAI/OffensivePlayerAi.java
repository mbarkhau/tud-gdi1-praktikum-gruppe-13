package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInGoalDirection;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInShootDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.SeeBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.TooFarFromBall;
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
		StudentHFSM<T> gotoBall = new GotoBall<T>(player);  // laufe zum Ball
		StudentHFSM<T> turnToBall = new TurnToBall<T>(player); // dreh dich zum Ball
		
		setInitialState(lookAhead);
		
		//addState(dribble);
		addState(lookAhead);
		addState(scout);
		addState(turnToGoal);
		addState(runOnGoal);
		addState(turnToBall);
		addState(drawNearBall);
		
		// lookAhead "weiss nicht wo Ball ist" Scout
		addTransition(lookAhead, scout, new NotExpression(new SeeBall((T) player.getEnv())));
		
		// lookAhead "weiss wo Ball ist und ist nicht am naechsten" TurnToGoal
		addTransition(lookAhead, turnToGoal, (new AndExpression(
									new SeeBall((T) player.getEnv()),new NotExpression(new IsClosestToBall<T>((T) player.getEnv())))));
		
		// Scout "Ball gefunden" lookAhead
		addTransition(scout.getName(), lookAhead.getName(), "found ball", new SeeBall<T>((T) player.getEnv()));
				
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
		
		// runOnGoal "sieht Ball UND nicht zu weit weg vom Ball UND nahe genug an Tor" lookAhead
		addTransition(runOnGoal.getName(), lookAhead.getName(), "is close enough to goal", new AndExpression<T>(
				new SeeBall<T>((T) player.getEnv()), new AndExpression<T>(
							new NotExpression<T>(new TooFarFromBall<T>((T) player.getEnv())),
							new IsInShootDistance<T>((T) player.getEnv()))));
		
		// runOnGoal "sieht Ball UND zu weit weg von Ball" turnToBall
		addTransition(runOnGoal.getName(), turnToBall.getName(), "to far from ball", new AndExpression<T>(
				new SeeBall<T>((T) player.getEnv()),new TooFarFromBall<T>((T) player.getEnv())));
		
		// turnToBall "schaut Richtung Ball" drawNearBall
		addTransition(turnToBall.getName(), drawNearBall.getName(), "is in Ball direction", new LookingAtBall<T>((T) player.getEnv()));
		
		// turnToBall "sieht Ball nicht mehr" lookAhead
		addTransition(turnToBall.getName(), lookAhead.getName(), "lost ball off eyes", new NotExpression<T>(new SeeBall<T>((T) player.getEnv())));
		
		// drawNearBall "verliert Ball aus Augen" lookAhead
		addTransition(drawNearBall.getName(), lookAhead.getName(), "lost ball off eyes", new NotExpression<T>(new SeeBall<T>((T) player.getEnv())));
		
		// drawNearBall "nicht mehr zu weit weg von Ball" lookAhead
		addTransition(drawNearBall.getName(), lookAhead.getName(), "not to far from ball", new NotExpression(new TooFarFromBall<T>((T) player.getEnv())));		
	}
}