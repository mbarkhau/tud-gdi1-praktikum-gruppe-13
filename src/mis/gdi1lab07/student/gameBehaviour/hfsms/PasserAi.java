package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Wait;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.AcceptorHasAknowledged;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAccepter;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PasserAi<T extends GameEnv> extends StudentHFSM<T> {
	
	public PasserAi(FieldPlayer<T> player) throws AutomatonException {

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
				new BallInDistance<T>((T) player.getEnv(), 0.5));
		
		addTransition(scout.getName(), findPassee.getName(), "find passee",
				new HasScouted<T>((T) player.getEnv()));
		
		addTransition(findPassee.getName(), requestPass.getName(), "request pass",
				new PlayerInDistance<T>((T) player.getEnv(), true, 80));
		
		addTransition(requestPass.getName(), anouncePass.getName(),
				"anounce pass", new HasHeardAccepter<T>((T) player.getEnv()));
		
		addTransition(anouncePass.getName(), doPass.getName(), "pass",
				new AcceptorHasAknowledged<T>((T) player.getEnv()));
		
		addTransition(doPass.getName(), wait.getName(), "wait",
				new BallPassedByMe<T>((T) player.getEnv()));
	}
}
