package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedToMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.GameIsOn;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class PassAI<T extends GameEnv> extends StudentHFSM<T> {

	public PassAI(FieldPlayer player) throws AutomatonException {

		StudentHFSM<T> passer = new PasserAi<T>(player);
		StudentHFSM<T> passee = new PasseeAi<T>(player);

		StudentHFSM<T> waitForKickoff = new WaitForKickoff<T>();
		StudentHFSM<T> scout = new Scout<T>(player);

		setInitialState(waitForKickoff);

		addState(passer);
		addState(passee);

		addState(waitForKickoff);
		addState(scout);

		addTransition(waitForKickoff.getName(), scout.getName(),
				"start scouting", new GameIsOn<T>((T) player.getEnv()));

		AndExpression<T> isPasser = new AndExpression<T>(new HasScouted<T>(
				(T) player.getEnv()), new IsClosestToBall<T>((T) player
				.getEnv()));

		addTransition(scout.getName(), passer.getName(), "is passer", isPasser);

		addTransition(passer.getName(), passee.getName(), "is passee",
				new NotExpression<T>(
						new IsClosestToBall<T>((T) player.getEnv())));

		addTransition(passer.getName(), passee.getName(), "is passe",
				new BallPassedByMe<T>((T) player.getEnv()));

		addTransition(passee.getName(), passer.getName(), "is passer",
				new BallPassedToMe<T>((T) player.getEnv()));


		addTransition(scout.getName(), passee.getName(), "is passee", new NotExpression<T>(isPasser));

	}

}
