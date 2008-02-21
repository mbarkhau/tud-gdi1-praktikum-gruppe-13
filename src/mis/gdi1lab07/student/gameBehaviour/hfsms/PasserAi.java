package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.AcceptorHasAknowledged;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.FoundPlayer;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAccepter;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PasserAi<T extends GameEnv> extends StudentHFSM<T> {
	
	public PasserAi(FieldPlayer player) throws AutomatonException {

		StudentHFSM<T> walk = new WalkToBall<T>(player);
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> findPassee = new FindPassee<T>(player);
		StudentHFSM<T> requestPass = new RequestPassTo<T>(player);
		StudentHFSM<T> anouncePass = new AnouncePass<T>(player);
		StudentHFSM<T> doPass = new Pass<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);

		setInitialState(walk);
		
		addState(walk);
		addState(scout);
		addState(findPassee);
		addState(requestPass);
		addState(anouncePass);
		addState(doPass);
		addState(wait);
		
		addTransition(walk.getName(), scout.getName(), "scout",
				new IsAtBall<T>((T) player.getEnv()));
		
		addTransition(scout.getName(), findPassee.getName(), "find passee",
				new HasScouted<T>((T) player.getEnv()));
		
		addTransition(findPassee.getName(), requestPass.getName(), "request pass",
				new FoundPlayer<T>((T) player.getEnv()));
		
		addTransition(requestPass.getName(), anouncePass.getName(),
				"anounce pass", new HasHeardAccepter<T>((T) player.getEnv()));
		
		addTransition(anouncePass.getName(), doPass.getName(), "pass",
				new AcceptorHasAknowledged<T>((T) player.getEnv()));
		
		addTransition(doPass.getName(), wait.getName(), "wait",
				new BallPassedByMe<T>((T) player.getEnv()));
	}
}
