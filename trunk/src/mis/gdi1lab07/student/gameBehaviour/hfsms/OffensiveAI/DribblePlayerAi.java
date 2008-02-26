package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.PasserAi;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtFlag;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInGoalDirection;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInShootDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.PlayerInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DribblePlayerAi<T extends GameEnv> extends StudentHFSM<T> {

	public DribblePlayerAi(FieldPlayer<T> player) throws AutomatonException{
		// TODO Auto-generated constructor stub
		
		StudentHFSM<T> turnToBall = new TurnToBall<T>(player);
		GotoBall<T> gotoBall = new GotoBall<T>(player);
		StudentHFSM<T> turnToGoal = new LookAtFlag<T>(player, FlagConstants.T_G_C);;
		StudentHFSM<T> dribble = new DribbleOnGoal<T>(player);
		StudentHFSM<T> shoot = new Shoot<T>(player);
		StudentHFSM<T> passer = new PasserAi<T>(player);
		
		setInitialState(turnToBall);
		
		gotoBall.setPower(100);
		
		addState(turnToBall);
		addState(gotoBall);
		addState(turnToGoal);
		addState(dribble);
		addState(shoot);
		addState(passer);
		
		// turnToBall "in Richtung Ball" gotoBall
		addTransition(turnToBall, gotoBall, new LookingAtBall<T>(player.getEnv()));
		
		// gotoBall "ist am Ball" turnToGoal
		addTransition(gotoBall, turnToGoal, new BallInDistance<T>(player.getEnv(),0.5));
		
		// turnToGoal "inRichtungTor und in Schussdistanz" shoot
		AndExpression<T> isInShootMood = new AndExpression<T>(new IsInGoalDirection<T>(player.getEnv()), new IsInShootDistance<T>((T) player.getEnv()));
		addTransition(turnToGoal, shoot, isInShootMood);
		
		// turnToGoal "inRichtungTor und ausserhalb Schussdistanz und kein enemyNear" dribble
		PlayerInDistance<T> enemyNear = new PlayerInDistance<T>(player.getEnv(), false, 3);
		AndExpression<T> inDribbleMode = new AndExpression<T>(new IsInGoalDirection<T>(player.getEnv()), new NotExpression<T>(new IsInShootDistance<T>((T) player.getEnv())));
		AndExpression<T> isNotInShootMood = new AndExpression<T>(new NotExpression<T>(enemyNear),inDribbleMode);
		AndExpression<T> isInPassMood = new AndExpression<T>(inDribbleMode, enemyNear);
		
		addTransition(turnToGoal, dribble, isNotInShootMood);
		
		// turnToGoal "inRichtungTor und ausserhalb Schussdistanz und enemy near" pass
		addTransition(turnToGoal, passer, isInPassMood);
				
		// dribble "ist nicht am Ball" turnToBall
		NotExpression<T> isAtBallNOT = new NotExpression<T>(new BallInDistance<T>(player.getEnv(),0.5));
		addTransition(dribble, turnToBall, isAtBallNOT);
		
		
		
		
	
	}

}
