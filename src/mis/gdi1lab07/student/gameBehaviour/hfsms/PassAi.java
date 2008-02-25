package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class PassAi<T extends GameEnv> extends StudentHFSM<T> {

	public PassAi(FieldPlayer<T> player) throws AutomatonException {
		T env = player.getEnv();
		int flagId = Utils.getPlayerPos(player.getNumber());

		StudentHFSM<T> passer = new PasserAi<T>(player);
		StudentHFSM<T> passee = new PasseeAi<T>(player);
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> gotoPos = new GotoFlag<T>(player, flagId);
		
		setInitialState(scout);

		addState(scout);
		addState(passer);
		addState(passee);
		addState(gotoPos);

		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		
		LogicExpression<T> closestToBall = new IsClosestToBall<T>(env);
		LogicExpression<T> notClosestToBall = new NotExpression<T>(closestToBall);
		
		LogicExpression<T> passedByMe = new BallPassedByMe<T>(env);
		LogicExpression<T> isPasser = new AndExpression<T>(hasScouted, closestToBall);
		LogicExpression<T> isPassee = new OrExpression<T>(new AndExpression<T>(hasScouted, notClosestToBall), passedByMe);
		
		LogicExpression<T> atPos = new FlagInDistance<T>(env, flagId, 5);
		LogicExpression<T> notAtPos = new NotExpression<T>(atPos);
		
		LogicExpression<T> returnToPos = new AndExpression<T>(isPassee, notAtPos);
		
		addTransition(scout, passer, isPasser);
		addTransition(passee, passer, isPasser);
		
		addTransition(scout, passee, isPassee);		
		
		addTransition(passer, gotoPos, returnToPos);
		addTransition(passee, gotoPos, returnToPos);
		
		addTransition(gotoPos, passee, atPos);

	}

}
