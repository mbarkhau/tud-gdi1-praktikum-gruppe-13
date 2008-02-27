package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class OffensiveAI<T extends GameEnv> extends BaseHfsm<T> {

	public OffensiveAI(FieldPlayer<T> player) throws AutomatonException {
		super(player);

		StudentHFSM<T> offensiv = new OffensivePlayerAi<T>(player);
		StudentHFSM<T> dribble = new DribblePlayerAi<T>(player);
		StudentHFSM<T> scout = new Scout<T>(player);

		setInitialState(scout);

		addState(offensiv);
		addState(dribble);
		addState(scout);

		LogicExpression<T> closestToBall = new IsClosestToBall<T>(env);
		LogicExpression<T> notClosestToBall = new NotExpression<T>(closestToBall);
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> hasNotScouted = new NotExpression<T>(hasScouted);
		
		LogicExpression<T> isOffensive = new AndExpression<T>(hasScouted, notClosestToBall);
		LogicExpression<T> isDribbler = new AndExpression<T>(hasScouted, closestToBall);
		
		addTransition(scout, offensiv, isOffensive);
		addTransition(scout, dribble, isDribbler);

		addTransition(offensiv, scout, hasNotScouted);
		addTransition(dribble, offensiv, notClosestToBall);

	}
}