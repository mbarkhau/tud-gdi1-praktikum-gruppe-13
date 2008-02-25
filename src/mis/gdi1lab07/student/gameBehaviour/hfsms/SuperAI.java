package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI.OffensiveAI;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Wait;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedToMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.GameIsOn;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class SuperAI<T extends GameEnv> extends StudentHFSM<T> {

	
	public SuperAI(FieldPlayer<T> player) throws AutomatonException{
		StudentHFSM<T> defense = new DefenseAI<T>(player);
		StudentHFSM<T> pass = new PasseeAi<T>(player);
		StudentHFSM<T> passer = new PasserAi<T>(player);
		StudentHFSM<T> offensive = new OffensiveAI<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);
		StudentHFSM<T> backToStart = new GoToStartPosition<T>(player);
		
		defense.setName("Defense");
		addState(defense);
		pass.setName("Pass annehmen");
		addState(pass);
		passer.setName("Passen");
		addState(passer);
		offensive.setName("Offensive");
		addState(offensive);
		wait.setName("Wait");
		addState(wait);
		backToStart.setName("Back to start");
		addState(backToStart);
		
		setInitialState(backToStart);
		
		//addTransition(backToStart, wait, new IsAtStartposition<T>((T) player.getEnv()));
		addTransition(wait, defense, new GameIsOn<T>((T) player.getEnv()));
		addTransition(defense, pass, new HasHeardRequest<T>((T) player.getEnv()));
		addTransition(pass, passer,new BallPassedToMe<T>((T) player.getEnv()));
		addTransition(passer, defense, new BallPassedByMe<T>((T) player.getEnv()));
		addTransition(offensive, pass, new HasHeardRequest<T>((T) player.getEnv()));
		
	}
	
}
