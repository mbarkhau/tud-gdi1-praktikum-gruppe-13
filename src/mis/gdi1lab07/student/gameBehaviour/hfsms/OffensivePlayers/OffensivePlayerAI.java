package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensivePlayers;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.AcceptPassFrom;
import mis.gdi1lab07.student.gameBehaviour.hfsms.AnouncePass;
import mis.gdi1lab07.student.gameBehaviour.hfsms.Pass;
import mis.gdi1lab07.student.gameBehaviour.hfsms.RequestPassTo;
import mis.gdi1lab07.student.gameBehaviour.hfsms.Scout;
import mis.gdi1lab07.student.gameBehaviour.hfsms.Wait;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WaitForKickoff;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WalkToBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.DribblePlayers.DribblePlayerAI;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.AcceptorHasAknowledged;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedToMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.GameIsOn;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAccepter;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

/**
 * Aufgabe 5.1
 * Offensivverhalten
 *
 * @param <T>
 */
public class OffensivePlayerAI<T extends GameEnv> extends StudentHFSM<T> {
	
	public OffensivePlayerAI(FieldPlayer player) throws AutomatonException{
		
		
		// 
		StudentHFSM<T> waitForKickoff = new WaitForKickoff<T>();
		
		// schaue nach vorne 
		StudentHFSM<T> lookAhead = new LookAhead<T>(player);
		
		// TODO DribblePlayer
		StudentHFSM<T> dribbler = new DribblePlayerAI<T>(player);
		
		// suche den Ball
		StudentHFSM<T> scout = new Scout<T>(player);
		
		// laufe ohne Ball auf Tor zu
		StudentHFSM<T> runAhead = new RunAhead<T>(player);
		
		// nähere dich Ball bis Höchstentfernung erreicht
		StudentHFSM<T> drawNearBall = new DrawNearBall<T>(player);
		
		// laufe zum Ball
		StudentHFSM<T> walk = new WalkToBall<T>(player);
		
		
		
		// noch kopiert von PassAI
		StudentHFSM<T> requestPass = new RequestPassTo<T>(player);
		StudentHFSM<T> anouncePass = new AnouncePass<T>(player);
		StudentHFSM<T> acceptPass = new AcceptPassFrom<T>(player);
		StudentHFSM<T> doPass = new Pass<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);
		
		
		// Hinzufügen der Zustände
		addState(lookAhead);
		addState(dribbler);
		addState(runAhead);
		addState(drawNearBall);
		addState(scout);
		
		
		setInitialState(waitForKickoff);
		
		//noch kopiert von PassAI
		addState(walk);
		addState(requestPass);
		addState(anouncePass);
		addState(acceptPass);
		addState(doPass);
		addState(wait);
		

		// Transitionen
		addTransition(waitForKickoff.getName(), scout.getName(), "start scouting", new GameIsOn<T>((T) player.getEnv()));
		
		
		// noch Kopie von der PassAI
//		addTransition(waitForKickoff.getName(), scout.getName(), "start scouting", new GameIsOn<T>((T) player.getEnv()));
//		AndExpression<T> isPasser = new AndExpression<T>(new HasScouted<T>((T) player.getEnv()), new IsClosestToBall<T>((T) player.getEnv()));
//		addTransition(scout.getName(), walk.getName(), "goto ball", isPasser);
//		addTransition(walk.getName(), requestPass.getName(), "request pass", new IsAtBall<T>((T) player.getEnv()));
//		addTransition(requestPass.getName(), anouncePass.getName(), "anounce pass", new HasHeardAccepter<T>((T) player.getEnv()));
//		addTransition(anouncePass.getName(), doPass.getName(), "pass", new AcceptorHasAknowledged<T>((T) player.getEnv()));
//		addTransition(doPass.getName(), wait.getName(), "waiting", new BallPassedByMe<T>((T) player.getEnv()));
//		
//		addTransition(scout.getName(), acceptPass.getName(), "accept pass", new HasHeardRequest<T>((T) player.getEnv()));
//		addTransition(acceptPass.getName(), walk.getName(), "goto ball", new BallPassedToMe<T>((T) player.getEnv()));
	}

}
