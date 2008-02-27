package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsClosestToBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.SeeFlag;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;


public class OffensiveAI<T extends GameEnv> extends BaseHfsm<T> implements FlagConstants{

	public OffensiveAI(FieldPlayer<T> player) throws AutomatonException {
		super(player);

		StudentHFSM<T> offensiv = new OffensivePlayerAi<T>(player);
		StudentHFSM<T> dribble = new DribblePlayerAi<T>(player);
		StudentHFSM<T> scout = new Scout<T>(player);
		GotoFlag<T> goBack = new GotoFlag<T>(player, env.getHomePos());
		
		goBack.setPower(20);
		
		setInitialState(scout);

		addState(offensiv);
		addState(dribble);
		addState(scout);
		addState(goBack);

		
		// ist er weiter als 40 weg von eigener Torlinie?
		LogicExpression<T> ownCornerRDist = new AndExpression<T>(
				new SeeFlag<T>(env,O_O_R),
				new NotExpression<T>(new FlagInDistance<T>(env,O_O_R,50)));
		LogicExpression<T> ownCornerLDist = new AndExpression<T>(
				new SeeFlag<T>(env,O_O_L),
				new NotExpression<T>(new FlagInDistance<T>(env,O_O_L,50)));;
		LogicExpression<T> ownGoalDist = new AndExpression<T>(
				new SeeFlag<T>(env,O_G_C),
				new NotExpression<T>(new FlagInDistance<T>(env,O_G_C,50)));
		
		// ist er näher als 66 dran an gegnerischer Linie
		LogicExpression<T> otherCornerRDist = new FlagInDistance<T>(env,T_O_R,58);
		LogicExpression<T> otherCornerLDist = new FlagInDistance<T>(env,T_O_L,58);
		LogicExpression<T> otherGoalDist = new FlagInDistance<T>(env,T_G_C,58);
		
		
		LogicExpression<T> inOtherHalfD = new OrExpression<T>(ownCornerRDist,ownCornerLDist);
		LogicExpression<T> inOtherHalfE = new OrExpression<T>(inOtherHalfD, ownGoalDist);

		LogicExpression<T> inOtherHalfF = new OrExpression<T>(otherCornerRDist,otherCornerLDist);
		LogicExpression<T> inOtherHalfG = new OrExpression<T>(inOtherHalfF,otherGoalDist);
		

		LogicExpression<T> inOpponentHalf = new OrExpression<T>(inOtherHalfE,inOtherHalfG);
		LogicExpression<T> inOwnHalf = new NotExpression<T>(inOpponentHalf);
		
		LogicExpression<T> closestToBall = new IsClosestToBall<T>(env);
		LogicExpression<T> notClosestToBall = new NotExpression<T>(closestToBall);
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> hasNotScouted = new NotExpression<T>(hasScouted);
		
		LogicExpression<T> hasNotScoutedInOpHalf = new AndExpression<T>(hasNotScouted, inOpponentHalf);
		LogicExpression<T> notClosestToBallinOpHalf = new AndExpression<T>(notClosestToBall, inOpponentHalf);
		LogicExpression<T> closestToBallInOpHalf = new AndExpression<T>(closestToBall, inOpponentHalf);
		
		LogicExpression<T> isOffensiveA = new AndExpression<T>(hasScouted, notClosestToBall);
		LogicExpression<T> isDribblerB = new AndExpression<T>(hasScouted, closestToBall);
		
		LogicExpression<T> isOffensive = new AndExpression<T>(inOpponentHalf, isOffensiveA);
		LogicExpression<T> isDribbler = new AndExpression<T>(inOpponentHalf, isDribblerB);
		
		LogicExpression<T> atHome = new FlagInDistance<T>(env, env.getHomePos(), 40);
		LogicExpression<T> ready = new AndExpression<T>(atHome, inOpponentHalf);
		LogicExpression<T> readyToGoAgain = new OrExpression<T>(ready, closestToBallInOpHalf);
		
		addTransition(scout, offensiv, isOffensive);
		addTransition(scout, dribble, isDribbler);

		addTransition(offensiv, scout, hasNotScoutedInOpHalf);
		addTransition(dribble, offensiv, notClosestToBallinOpHalf);
		
		addTransition(offensiv, goBack,inOwnHalf);
		addTransition(dribble, goBack, inOwnHalf);
		
		addTransition(goBack, scout, readyToGoAgain);
	}
}