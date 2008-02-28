package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Wait;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
public class JoggerAi<T extends GameEnv> extends BaseHfsm<T> {

	public JoggerAi(FieldPlayer<T> player) throws AutomatonException {
		super(player);
		GotoFlag<T> goOurGoal = new GotoFlag<T>(player, O_G_C, 100);
		goOurGoal.setName("ourGoal");
		GotoFlag<T> goTheirGoal = new GotoFlag<T>(player, T_G_C, 100);
		goOurGoal.setName("theirGoal");
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);
		
		addState(scout);
		addState(goOurGoal);
		addState(goTheirGoal);
		addState(wait);
		
		setInitialState(scout);
		
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> atOurGoal = new FlagInDistance<T>(env, O_G_C, 4);
		LogicExpression<T> atTheirGoal = new FlagInDistance<T>(env, T_G_C, 4);
		
		addTransition(scout, goOurGoal, hasScouted);
		addTransition(goOurGoal, goTheirGoal, atOurGoal);
		addTransition(goTheirGoal, goOurGoal, atTheirGoal);
		
	}

}
