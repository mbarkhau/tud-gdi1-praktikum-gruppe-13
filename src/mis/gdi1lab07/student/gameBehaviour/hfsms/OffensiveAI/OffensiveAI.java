package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.PasseeAi;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Wait;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.SeeBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.GameIsOn;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class OffensiveAI<T extends GameEnv> extends StudentHFSM<T> {

	public OffensiveAI(FieldPlayer player) throws AutomatonException {
		
		StudentHFSM<T> offensiv = new OffensivePlayerAi<T>(player);
		StudentHFSM<T> dribble = new DribblePlayerAi<T>(player);
		StudentHFSM<T> waiting = new Wait<T>(player);

		setInitialState(waiting);

		addState(offensiv);
		addState(dribble);
		addState(waiting);
		//addState(goToBall);
		
		// wait "KickOff" offensiv
		addTransition(waiting, offensiv, new GameIsOn<T>((T) player.getEnv()));

		// offensiv "ist am naechsten" dribble
		addTransition(offensiv, dribble, new IsClosestToBall<T>((T) player.getEnv()));

		// dribble "sieht Ball nicht oder sieht ihn und ist nicht am n�chsten zum Ball" offensiv
		addTransition(dribble, offensiv, new OrExpression<T>(
				new NotExpression<T>(new SeeBall<T>((T) player.getEnv())),
				new AndExpression<T>(
						new SeeBall<T>((T) player.getEnv()),
						new NotExpression<T>(new IsClosestToBall<T>((T) player.getEnv())))));
	}
}