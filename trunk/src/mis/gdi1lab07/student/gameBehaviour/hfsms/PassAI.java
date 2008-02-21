package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.GameIsOn;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAccepter;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PassAI<T extends GameEnv> extends StudentHFSM<T> {
	
	public PassAI(FieldPlayer player) throws AutomatonException{
		
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> walk = new WalkToBall<T>(player);
		StudentHFSM<T> requestPass = new RequestPassTo<T>(player);
		StudentHFSM<T> anouncePass = new AnouncePass<T>(player);
		StudentHFSM<T> acceptPass = new AcceptPassFrom<T>(player);
		StudentHFSM<T> doPass = new Pass<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);
		StudentHFSM<T> waitForKickoff = new WaitForKickoff<T>();
		
		setInitialState(waitForKickoff);

		addState(scout);
		addState(walk);
		addState(requestPass);
		addState(anouncePass);
		addState(acceptPass);
		addState(doPass);
		addState(wait);
		addState(waitForKickoff);
		
		addTransition(waitForKickoff.getName(), scout.getName(), "start scouting", new GameIsOn<T>((T) player.getEnv()));
		AndExpression<T> isPasser = new AndExpression<T>(new HasScouted<T>((T) player.getEnv()), new IsClosestToBall<T>((T) player.getEnv()));
		addTransition(scout.getName(), walk.getName(), "goto ball", isPasser);
		addTransition(walk.getName(), requestPass.getName(), "request pass", new IsAtBall<T>((T) player.getEnv()));
		addTransition(requestPass.getName(), anouncePass.getName(), "anounce pass", new HasHeardAccepter<T>((T) player.getEnv()));
//		addTransition(anouncePass.getName(), doPass.getName(), "pass", new GameIsOn<T>(player.getEnv()));
//		addTransition(doPass.getName(), wait.getName(), "waiting", new GameIsOn<T>(player.getEnv()));
//		
//		addTransition(scout.getName(), acceptPass.getName(), "accept pass", new GameIsOn<T>(player.getEnv()));
//		addTransition(acceptPass.getName(), walk.getName(), "goto ball", new GameIsOn<T>(player.getEnv()));
	}

}
