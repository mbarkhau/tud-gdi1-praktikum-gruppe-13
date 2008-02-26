package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtFlag;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInGoalDirection;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInShootDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtBall;
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
		
		setInitialState(turnToBall);
		
		gotoBall.setPower(100);
		
		addState(turnToBall);
		addState(gotoBall);
		addState(turnToGoal);
		addState(dribble);
		addState(shoot);
		
		// turnToBall "in Richtung Ball" gotoBall
		addTransition(turnToBall, gotoBall, new LookingAtBall<T>(player.getEnv()));
		
		// gotoBall "ist am Ball" turnToGoal
		addTransition(gotoBall, turnToGoal, new BallInDistance<T>(player.getEnv(),0.5));
		
		// turnToGoal "inRichtungTor und in Schussdistanz" shoot
		AndExpression<T> isInShootMood = new AndExpression<T>(new IsInGoalDirection<T>(player.getEnv()), new IsInShootDistance<T>((T) player.getEnv()));
		addTransition(turnToGoal, shoot, isInShootMood);
		
		// turnToGoal "inRichtungTor und ausserhalb Schussdistanz" dribble
		AndExpression<T> isNotInShootMood = 
				new AndExpression<T>(new IsInGoalDirection<T>(player.getEnv()), new NotExpression<T>(new IsInShootDistance<T>((T) player.getEnv())));
		addTransition(turnToGoal, dribble, isNotInShootMood);
		
		// dribble "ist nicht am Ball" turnToBall
		NotExpression<T> isAtBallNOT = new NotExpression<T>(new BallInDistance<T>(player.getEnv(),0.5));
		addTransition(dribble, turnToBall, isAtBallNOT);
		
		
		
		
	
	}

}
