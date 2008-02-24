package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WalkToBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.LookAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.InShootDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.LookingAtFlag;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class DribblePlayerAi<T extends GameEnv> extends StudentHFSM<T> {

	public DribblePlayerAi(FieldPlayer<T> player) throws AutomatonException{
		// TODO Auto-generated constructor stub
		
		StudentHFSM<T> turnToBall = new LookAtBall<T>(player);
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
		AndExpression<T> isInShootMood = new AndExpression<T>(new LookingAtFlag<T>(player.getEnv(), T_G_C), new InShootDistance<T>((T) player.getEnv()));
		addTransition(turnToGoal.getName(), shoot.getName(), "is in shoot distance", isInShootMood);
		
		// turnToGoal "inRichtungTor und au�erhalb Schussdistanz" dribble
		NotExpression<T> isNotInShootMood = new NotExpression<T>(isInShootMood);
		addTransition(turnToGoal.getName(), dribble.getName(), "is not in shoot distance", isNotInShootMood);
		
		// dribble "ist nicht am Ball" turnToBall
		NotExpression<T> isAtBallNOT = new NotExpression<T>(new BallInDistance<T>(player.getEnv(), 0.5));
		addTransition(dribble.getName(), turnToBall.getName(), "is not at ball", isAtBallNOT);
		
		
		
	
	}

}
