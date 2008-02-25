package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoPlayer;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallLessThan30MetersAway;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.EnemyLessThan10MetersAway;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtPosition;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DefenseAI<T extends GameEnv> extends StudentHFSM<T> {

	public DefenseAI(FieldPlayer<T> player) throws AutomatonException {
		StudentHFSM<T> goBack = new GotoFlag<T>(player, player.getEnv().getPlayerPosition());
		StudentHFSM<T> followEnemy = new GotoPlayer<T>(player, false, -1);
		StudentHFSM<T> followBall = new GotoBall<T> (player);
		StudentHFSM<T> pass = new PasserAi<T> (player);
		StudentHFSM<T> scout = new Scout<T> (player);
		StudentHFSM<T> hasScouted = new Scout<T> (player);
		hasScouted.setName("hasScouted");
		
		setInitialState(scout);
		
		addState(goBack);
		addState(scout);
		addState(hasScouted);
		addState(followEnemy);
		addState(followBall);
		addState(pass);
		
		addTransition(scout.getName(), goBack.getName(), "has scouted", new HasScouted<T>((T) player.getEnv()));
		addTransition(goBack.getName(), hasScouted.getName(), "reachedPosition", new FlagInDistance<T>(player.getEnv(), player.getEnv().getPlayerPosition(), 8));
		addTransition(hasScouted.getName(), followEnemy.getName(), "follow enemy", new AndExpression<T>(new BallInDistance<T>((T) player.getEnv(), 30), new PlayerInDistance<T>(player.getEnv(), false, 10)));
		addTransition(hasScouted.getName(), followBall.getName(), "follow ball", new AndExpression<T>(new BallInDistance<T>((T) player.getEnv(), 10), new IsClosestToBall<T>((T) player.getEnv())));
		addTransition(goBack.getName(), followEnemy.getName(), "follow enemy", new AndExpression<T>(new BallInDistance<T>((T) player.getEnv(), 30), new PlayerInDistance<T>(player.getEnv(), false, 10)));
		addTransition(goBack.getName(), followBall.getName(), "follow ball", new AndExpression<T>(new BallInDistance<T>((T) player.getEnv(), 10), new IsClosestToBall<T>((T) player.getEnv())));
		addTransition(followEnemy.getName(), followBall.getName(), "follow ball", new AndExpression<T>(new BallInDistance<T>((T) player.getEnv(), 10), new IsClosestToBall<T>((T) player.getEnv())));
		addTransition(followBall.getName(), pass.getName(), "pass ball", new BallInDistance<T>((T) player.getEnv(), 0.5));
	
	}
}
