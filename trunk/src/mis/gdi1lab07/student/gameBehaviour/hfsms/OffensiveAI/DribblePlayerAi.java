package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WalkToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInGoalDirection;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInShootDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DribblePlayerAi<T extends GameEnv> extends StudentHFSM<T> {

	public DribblePlayerAi(FieldPlayer player) throws AutomatonException{
		// TODO Auto-generated constructor stub
		
		StudentHFSM<T> turnToBall = new TurnToBall<T>(player);
		StudentHFSM<T> walkToBall = new WalkToBall<T>(player);
		StudentHFSM<T> turnToGoal = new TurnToGoal<T>(player);
		StudentHFSM<T> dribble = new DribbleOnGoal<T>(player);
		StudentHFSM<T> shoot = new Shoot<T>(player);
		
		setInitialState(turnToBall);
		
		addState(turnToBall);
		addState(walkToBall);
		addState(turnToGoal);
		addState(dribble);
		addState(shoot);
		
		// turnToBall "in Richtung Ball" WalkToBall
		addTransition(turnToBall.getName(), walkToBall.getName(), "is in ball direction", new LookingAtBall<T>((T) player.getEnv()));
		
		// walkToBall "ist am Ball" turnToGoal
		addTransition(walkToBall.getName(), turnToGoal.getName(), "is at ball", new BallInDistance<T>((T) player.getEnv(), 0.5));
		
		// turnToGoal "inRichtungTor und in Schussdistanz" shoot
		AndExpression<T> isInShootMood = new AndExpression<T>(new IsInGoalDirection<T>((T) player.getEnv()), new IsInShootDistance<T>((T) player.getEnv()));
		addTransition(turnToGoal.getName(), shoot.getName(), "is in shoot distance", isInShootMood);
		
		// turnToGoal "inRichtungTor und auﬂerhalb Schussdistanz" dribble
		AndExpression<T> isNotInShootMood = 
				new AndExpression<T>(new IsInGoalDirection<T>((T) player.getEnv()), new NotExpression<T>(new IsInShootDistance<T>((T) player.getEnv())));
		addTransition(turnToGoal.getName(), dribble.getName(), "is not in shoot distance", isNotInShootMood);
		
		// dribble "ist nicht am Ball" turnToBall
		NotExpression<T> isAtBallNOT = new NotExpression(new BallInDistance<T>((T) player.getEnv(),0.5));
		addTransition(dribble.getName(), turnToBall.getName(), "is not at ball", isAtBallNOT);
		
		
		
		
	
	}

}
