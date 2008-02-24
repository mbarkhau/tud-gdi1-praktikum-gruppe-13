package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.PasseeAi;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Wait;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallVisible;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.GameIsOn;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class OffensiveAI<T extends GameEnv> extends StudentHFSM<T> {

	public OffensiveAI(FieldPlayer player) throws AutomatonException {

		StudentHFSM<T> offensiv = new OffensivePlayerAi<T>(player);
		StudentHFSM<T> dribble = new DribblePlayerAi<T>(player);
		StudentHFSM<T> passee = new PasseeAi<T>(player);
		//StudentHFSM<T> backy = new BackToPositionPlayer<T>(player);
		
		StudentHFSM<T> wait = new Wait<T>(player);

		setInitialState(wait);

		addState(offensiv);
		addState(dribble);
		addState(passee);
		addState(wait);
		
		// waitForKickoff "KickOff" offensiv
		addTransition(wait.getName(), offensiv.getName(),
				"kickoff", new GameIsOn<T>((T) player.getEnv()));
		
		// offensiv "ist am n�hsten" dribble
		addTransition(offensiv.getName(), dribble.getName(), "is Closest to Ball", new IsClosestToBall<T>((T) player.getEnv()));
		
		// offensiv "h�rt Passanfrage" passee
		addTransition(offensiv.getName(), passee.getName(), "hear pass request", new HasHeardRequest<T>((T) player.getEnv()));
		
		// dribble "verliert Ball aus Augen" offensiv
		addTransition(dribble.getName(), offensiv.getName(), "lost ball of eyes", new NotExpression<T>(new BallVisible<T>((T) player.getEnv())));
		
		// dribble "ist nicht am n�chsten zum Ball" offensiv
		addTransition(dribble.getName(), offensiv.getName(), "is not closest to ball", new NotExpression<T>(new IsClosestToBall<T>((T) player.getEnv())));
		
		// dribble "h�rt Passanfrage" passee
		addTransition(dribble.getName(), passee.getName(), "hear pass request", new HasHeardRequest<T>((T) player.getEnv()));

	}
}