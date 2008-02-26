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
		
		// lookAhead "weiss nicht wo Ball ist" Scout
		addTransition(lookAhead, scout, new NotExpression<T>(new SeeBall<T>((T) player.getEnv())));
		
		// lookAhead "weiss wo Ball ist, zu weit vom Ball und nicht an Startposition" goBack
		addTransition(lookAhead, goBack, new AndExpression<T>(
							new NotExpression<T>(new FlagInDistance<T>(player.getEnv(), player.getEnv().getHomePos(), 25)),
							new AndExpression<T>(
									new SeeBall<T>((T) player.getEnv()), 
									new TooFarFromBall<T>((T) player.getEnv()))));
		
		// lookAhead "weiss wo Ball ist, zu weit vom Ball und an Startposition" drawNearBall
		addTransition(lookAhead, turnToBall, new AndExpression<T>(
							new FlagInDistance<T>(player.getEnv(), player.getEnv().getHomePos(), 10),
							new AndExpression<T>(
									new SeeBall<T>((T) player.getEnv()), 
									new TooFarFromBall<T>((T) player.getEnv()))));
		
		
		// lookAhead "weiss wo Ball ist und ist nicht zu weit enftfernt" TurnToGoal
		addTransition(lookAhead, turnToGoal, (new AndExpression<T>(
				new NotExpression<T>(new TooFarFromBall<T>((T) player.getEnv())),
				new SeeBall<T>((T) player.getEnv()))));
		
		// Scout "Ball gefunden" lookAhead
		addTransition(scout, lookAhead, new SeeBall<T>((T) player.getEnv()));
				
		// turnToGoal "sieht Ball und in Tor-Richtung " runOnGoal
		addTransition(turnToGoal, runOnGoal, new AndExpression<T>(
						new SeeBall<T>((T) player.getEnv()), 
						new IsInGoalDirection<T>((T) player.getEnv())));
		
		// turnToGoal "verliert ball aus Augen" lookAhead
		addTransition(turnToGoal, lookAhead, new NotExpression<T>(new SeeBall<T>((T) player.getEnv())) );
		
		// runOnGoal "sieht Ball und ist nicht in Tor-Richtung" lookAhead
		addTransition(runOnGoal, lookAhead, new AndExpression<T>(
							new SeeBall<T>((T)player.getEnv()),
							new NotExpression<T>(new IsInGoalDirection<T>((T) player.getEnv()))));
		
		// runOnGoal "verliert ball aus Augen" lookAhead
		addTransition(runOnGoal, lookAhead, new NotExpression<T>(new SeeBall<T>((T) player.getEnv())));
		
		// runOnGoal "sieht Ball UND nicht zu weit weg vom Ball UND nahe genug an Tor" lookAhead
		addTransition(runOnGoal, lookAhead, new AndExpression<T>(
				new SeeBall<T>((T) player.getEnv()), new AndExpression<T>(
							new NotExpression<T>(new TooFarFromBall<T>((T) player.getEnv())),
							new IsInShootDistance<T>((T) player.getEnv()))));
		
		// runOnGoal "sieht Ball UND zu weit weg von Ball" lookAhead
		addTransition(runOnGoal, lookAhead, new AndExpression<T>(
				new SeeBall<T>((T) player.getEnv()),new TooFarFromBall<T>((T) player.getEnv())));
		
		// turnToBall "schaut Richtung Ball" drawNearBall
		addTransition(turnToBall, drawNearBall, new LookingAtBall<T>((T) player.getEnv()));
		
		// turnToBall "sieht Ball nicht mehr" lookAhead
		addTransition(turnToBall, lookAhead, new NotExpression<T>(new SeeBall<T>((T) player.getEnv())));
		
		// drawNearBall "verliert Ball aus Augen" lookAhead
		addTransition(drawNearBall, lookAhead, new NotExpression<T>(new SeeBall<T>((T) player.getEnv())));
		
		// drawNearBall "sieht Ball und (schaut nicht Richtung Ball oder nicht mehr zu weit weg)" lookAhead
		addTransition(drawNearBall, lookAhead, new AndExpression<T>(
									new SeeBall<T>((T) player.getEnv()),
									new OrExpression<T>(
											new NotExpression<T>(new TooFarFromBall<T>((T) player.getEnv())),
											new NotExpression<T>(new LookingAtBall<T>((T) player.getEnv())))));
		
		// goBack "ist zur�ck" lookAhead
		addTransition(goBack, lookAhead, new FlagInDistance<T>(player.getEnv(), player.getEnv().getHomePos(), 8));
		
	}
}