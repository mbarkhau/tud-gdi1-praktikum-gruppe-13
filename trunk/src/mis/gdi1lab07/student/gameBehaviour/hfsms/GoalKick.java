package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Shoot;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class GoalKick<T extends GameEnv> extends BaseHfsm<T> {

	public GoalKick(FieldPlayer<T> player) throws AutomatonException {
		super(player);
		
		StudentHFSM<T> gotoBall = new GotoBall<T>(player);
		StudentHFSM<T> shoot = new Shoot<T>(player);
		
		setInitialState(gotoBall);
		
		addState(gotoBall);
		addState(shoot);
		
		LogicExpression<T> isAtBall = new BallInDistance<T>(player.getEnv(),0.5);
		
		addTransition(gotoBall,shoot, isAtBall);
	}

}
