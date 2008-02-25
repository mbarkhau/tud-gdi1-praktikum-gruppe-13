package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtPosition;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallVisible;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtFlag;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GoalieAi<T extends GameEnv> extends StudentHFSM<T> {

	public GoalieAi(FieldPlayer<T> player) throws AutomatonException {
		
		player.setNumber(1);
		
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> gotoGoal = new GotoFlag<T>(player, FlagConstants.O_G_C);
		StudentHFSM<T> watchBall = new LookAtBall<T>(player);
		StudentHFSM<T> grabBall = new LookAtBall<T>(player);
		StudentHFSM<T> passer = new PasserAi<T>(player);
		StudentHFSM<T> kick = new LookAtBall<T>(player);
		StudentHFSM<T> gotoBall = new LookAtBall<T>(player);

		addState(scout);
		addState(gotoGoal);
		addState(watchBall);
		addState(grabBall);
		addState(passer);
		addState(kick);
		addState(gotoBall);

		setInitialState(scout);
		
		//Goalie im Strafraum, wenn er mittlere Strafraumflagge sieht, aber nicht mittlere Torflagge, oder umgekehrt
		AndExpression<T> isInPenaltyArea1 = new AndExpression<T>(new LookingAtFlag<T>(player.getEnv(), O_P_C), new NotExpression<T>( new LookingAtFlag<T>(player.getEnv(), O_G_C)));
		AndExpression<T> isInPenaltyArea2 = new AndExpression<T>(new LookingAtFlag<T>(player.getEnv(), O_G_C), new NotExpression<T>( new LookingAtFlag<T>(player.getEnv(), O_P_C)));
		OrExpression<T> isInPenaltyArea = new OrExpression<T>(isInPenaltyArea1, isInPenaltyArea2);
		
		//Goalie darf Ball nehmen, wenn er im Strafraum ist und der Ball weniger als 2 Meter entfernt ist
		AndExpression<T> readyToGrab = new AndExpression<T>(isInPenaltyArea, new BallInDistance<T>(player.getEnv(), 2));
		
		//Feind attackiert, sobald er und der Ball weniger als 20 Meter vom Tor entfernt sind
		AndExpression<T> enemyAttacks = new AndExpression<T>(new BallInDistance<T>(player.getEnv(), 20), new PlayerInDistance<T>(player.getEnv(), false, 20));
		
		//Goalie hat Ball gefangen, er ist somit weniger als 1 Meter entfernt
		BallInDistance<T> grabbedBall = new BallInDistance<T>(player.getEnv(), 1);
		
		//Wenn der Ball mehr als 40 Meter entfernt ist
		NotExpression<T> ballAway = new NotExpression<T>(new BallInDistance<T>(player.getEnv(), 40));
		
		addTransition(scout.getName(), watchBall.getName(), "watch ball", new BallVisible<T>((T) player.getEnv()));
		addTransition(watchBall.getName(), scout.getName(), "scout", new NotExpression<T>(new BallVisible<T>((T) player.getEnv())));
		addTransition(watchBall.getName(), grabBall.getName(), "grab ball", readyToGrab);
		addTransition(watchBall.getName(), gotoBall.getName(), "goto ball", enemyAttacks);
		addTransition(grabBall.getName(), passer.getName(), "pass ball", grabbedBall);
		addTransition(passer.getName(), gotoGoal.getName(), "go back", ballAway);
		addTransition(gotoBall.getName(), gotoGoal.getName(), "go back", ballAway);
		addTransition(grabBall.getName(), gotoGoal.getName(), "go back", ballAway);
		addTransition(scout.getName(), gotoGoal.getName(), "go back", ballAway);
		addTransition(gotoGoal.getName(), scout.getName(), "is back", new IsAtPosition<T>(player.getEnv()));
	}

}
