package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.AcceptorHasAknowledged;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedToMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.GameIsOn;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAccepter;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.SeeBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameBehaviour.hfsms.*;

public class OffensiveAI<T extends GameEnv> extends StudentHFSM<T> {

	public OffensiveAI(FieldPlayer player) throws AutomatonException {

		StudentHFSM<T> offensiv = new OffensivePlayerAi<T>(player);
		StudentHFSM<T> dribble = new DribblePlayerAi<T>(player);
		StudentHFSM<T> passee = new PasseeAi<T>(player);
		//StudentHFSM<T> backy = new BackToPositionPlayer<T>(player);
		
		StudentHFSM<T> waitForKickoff = new WaitForKickoff<T>();

		setInitialState(waitForKickoff);

		addState(offensiv);
		addState(dribble);
		addState(passee);

		addState(waitForKickoff);
		
		// waitForKickoff "KickOff" offensiv
		addTransition(waitForKickoff.getName(), offensiv.getName(),
				"kickoff", new GameIsOn<T>((T) player.getEnv()));
		
		// offensiv "ist am n�hsten" dribble
		addTransition(offensiv.getName(), dribble.getName(), "is Closest to Ball", new IsClosestToBall<T>((T) player.getEnv()));
		
		// offensiv "h�rt Passanfrage" passee
		addTransition(offensiv.getName(), passee.getName(), "hear pass request", new HasHeardRequest<T>((T) player.getEnv()));
		
		// dribble "verliert Ball aus Augen" offensiv
		NotExpression<T> seeBallNOT = new NotExpression(new SeeBall<T>((T) player.getEnv()));
		addTransition(dribble.getName(), offensiv.getName(), "lost ball of eyes", seeBallNOT);
		
		// dribble "ist nicht am n�chsten zum Ball" offensiv
		NotExpression<T> isNotClosestToBall = new NotExpression(new IsClosestToBall<T>((T) player.getEnv()));
		addTransition(dribble.getName(), offensiv.getName(), "is not closest to ball", isNotClosestToBall);
		
		// dribble "h�rt Passanfrage" passee
		addTransition(dribble.getName(), passee.getName(), "hear pass request", new HasHeardRequest<T>((T) player.getEnv()));

	}
}