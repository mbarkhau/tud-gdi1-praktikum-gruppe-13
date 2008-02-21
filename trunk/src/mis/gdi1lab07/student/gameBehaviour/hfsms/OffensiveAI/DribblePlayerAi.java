package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WalkToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsInBallDirection;
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
		addTransition(turnToBall.getName(), walkToBall.getName(), "is in ball direction", new IsInBallDirection<T>((T) player.getEnv()));
		
		// walkToBall "ist am Ball" turnToGoal
		addTransition(walkToBall.getName(), turnToGoal.getName(), "is at ball", new IsAtBall<T>((T) player.getEnv()));
		
		// turnToGoal "inRichtungTor und in Schussdistanz" shoot
		AndExpression<T> isInShootMood = new AndExpression<T>(new IsInGoalDirection<T>((T) player.getEnv()), new IsInShootDistance<T>((T) player.getEnv()));
		addTransition(turnToGoal.getName(), shoot.getName(), "is in shoot distance", isInShootMood);
		
		// turnToGoal "inRichtungTor und auﬂerhalb Schussdistanz" dribble
		NotExpression<T> isNotInShootMood = new NotExpression<T>(isInShootMood);
		addTransition(turnToGoal.getName(), dribble.getName(), "is not in shoot distance", isNotInShootMood);
		
		// dribble "ist nicht am Ball" turnToBall
		NotExpression<T> isAtBallNOT = new NotExpression(new IsAtBall<T>((T) player.getEnv()));
		addTransition(dribble.getName(), turnToBall.getName(), "is not at ball", isAtBallNOT);
		
		
		
	
	}

}
