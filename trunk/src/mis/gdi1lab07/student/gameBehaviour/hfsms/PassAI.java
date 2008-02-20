package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.GameIsOn;
import mis.gdi1lab07.student.gameData.FieldPlayer;

public class PassAI<T> extends StudentHFSM<T> {
	
	public PassAI(FieldPlayer player) throws AutomatonException{
		setInitialState(new Wait<T>(player));
		addState(getInitialState());
		
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> walk = new WalkToBall<T>(player);
		StudentHFSM<T> requestPass = new RequestPassTo<T>(player);
		StudentHFSM<T> anouncePass = new AnouncePass<T>(player);
		StudentHFSM<T> acceptPass = new AcceptPassFrom<T>(player);
		StudentHFSM<T> doPass = new Pass<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);
		StudentHFSM<T> waitForKickoff = new WaitForKickoff<T>();

		addState(scout);
		addState(walk);
		addState(requestPass);
		addState(anouncePass);
		addState(acceptPass);
		addState(doPass);
		addState(wait);
		addState(waitForKickoff);
		
		addTransition(waitForKickoff.getName(), scout.getName(), "start scouting", new GameIsOn<T>());
		addTransition(scout.getName(), walk.getName(), "goto ball", new GameIsOn<T>());
		addTransition(walk.getName(), requestPass.getName(), "request pass", new GameIsOn<T>());
		addTransition(requestPass.getName(), anouncePass.getName(), "anounce pass", new GameIsOn<T>());
		addTransition(anouncePass.getName(), doPass.getName(), "pass", new GameIsOn<T>());
		addTransition(doPass.getName(), wait.getName(), "waiting", new GameIsOn<T>());
		
		addTransition(scout.getName(), acceptPass.getName(), "accept pass", new GameIsOn<T>());
		addTransition(acceptPass.getName(), walk.getName(), "goto ball", new GameIsOn<T>());
	}

}
