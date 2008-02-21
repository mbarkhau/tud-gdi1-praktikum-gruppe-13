package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class SuperAI<T extends GameEnv> extends StudentHFSM<T> {

	
	public SuperAI(FieldPlayer player) throws AutomatonException{
		StudentHFSM<T> defense = new DefenseAI<T>(player);
		StudentHFSM<T> pass = new PassAI<T>(player);
		
		addState(defense);
		addState(pass);
		
		setInitialState(pass);
		
		addTransition(defense.getName(), pass.getName(), "switch pass", new HasHeardRequest<T>((T) player.getEnv()));
		
		
	}
	
}
