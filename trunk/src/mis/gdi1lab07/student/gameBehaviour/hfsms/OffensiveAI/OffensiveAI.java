package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
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
		
		
		// offensiv/dribble "Spielunterbrechung" waitForKickoff
		// TODO muss noch hinzugefügt werden, im Gesamtkonzept dann
		
		// offensiv "ist am nähsten" dribble
		addTransition(offensiv.getName(), dribble.getName(), "is Closest to Ball", new IsClosestToBall<T>((T) player.getEnv()));
		
		// offensiv "hört Passanfrage" passee
		addTransition(offensiv.getName(), passee.getName(), "hear pass request", new HasHeardRequest<T>((T) player.getEnv()));
		

		// dribble "sieht Ball nicht oder sieht ihn und ist nicht am nächsten zum Ball" offensiv
		addTransition(dribble.getName(), offensiv.getName(), "is not closest to ball", new OrExpression<T>(
				new NotExpression<T>(new SeeBall<T>((T) player.getEnv())),
				new AndExpression<T>(
						new SeeBall<T>((T) player.getEnv()),
						new NotExpression<T>(new IsClosestToBall<T>((T) player.getEnv())))));
		
		// dribble "hört Passanfrage" passee
		addTransition(dribble.getName(), passee.getName(), "hear pass request", new HasHeardRequest<T>((T) player.getEnv()));

	}
}