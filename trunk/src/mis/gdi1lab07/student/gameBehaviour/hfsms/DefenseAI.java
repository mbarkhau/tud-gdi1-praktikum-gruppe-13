package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallLessThan10MetersAway;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallLessThan30MetersAway;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.EnemyLessThan10MetersAway;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DefenseAI<T extends GameEnv> extends StudentHFSM<T> {

	public DefenseAI(FieldPlayer player) throws AutomatonException {
		StudentHFSM<T> goBack = new GoToStartPosition<T>(player);
		StudentHFSM<T> followEnemy = new FollowEnemyPlayer<T>(player);
		StudentHFSM<T> followBall = new WalkToBall<T> (player);
		StudentHFSM<T> pass = new PasserAi<T> (player);
		
		setInitialState(goBack);
		
		addState(goBack);
		addState(followEnemy);
		addState(followBall);
		addState(pass);
		
		addTransition(goBack.getName(), followEnemy.getName(), "follow enemy", new AndExpression(new BallLessThan30MetersAway<T>((T) player.getEnv()), new EnemyLessThan10MetersAway<T>((T) player.getEnv())));
		addTransition(goBack.getName(), followBall.getName(), "follow ball", new AndExpression(new BallLessThan10MetersAway<T>((T) player.getEnv()), new IsClosestToBall<T>((T) player.getEnv())));
		addTransition(followEnemy.getName(), followBall.getName(), "follow ball", new AndExpression(new BallLessThan10MetersAway<T>((T) player.getEnv()), new IsClosestToBall<T>((T) player.getEnv())));
		addTransition(followBall.getName(), pass.getName(), "pass ball", new IsAtBall<T>((T) player.getEnv()));
	
	}
}
