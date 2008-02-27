package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.PasseeAi;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardAck;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.HasScouted;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;


public class OffensivePlayerAi<T extends GameEnv> extends BaseHfsm<T> {

	public OffensivePlayerAi(FieldPlayer<T> player) throws AutomatonException {  
		super(player);
		
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> passee = new PasseeAi<T>(player);
		StudentHFSM<T> gotoBall = new GotoBall<T>(player);
		StudentHFSM<T> goBack = new GotoFlag<T>(player, env.getHomePos());
		
		setInitialState(scout);
		
		addState(scout);
		addState(passee);
		addState(gotoBall);
		addState(goBack);
		
		LogicExpression<T> atHome = new FlagInDistance<T>(env, env.getHomePos(), 8);
		LogicExpression<T> notAtHome = new NotExpression<T>(atHome);
		
		LogicExpression<T> hasScouted = new HasScouted<T>(env);
		LogicExpression<T> hasNotScouted = new NotExpression<T>(hasScouted);
		
		// Pr�ft ob die Entfernung zum Ball zu gro� ist (Aufg. 5.1 Hinweis 2.4)
		LogicExpression<T> ballNotTooFar = new BallInDistance<T>(env, 20);
		LogicExpression<T> ballTooFar = new NotExpression<T>(ballNotTooFar);
		
		LogicExpression<T> hasHeardRequest = new HasHeardRequest<T>(env);
		LogicExpression<T> hasHeardAck = new HasHeardAck<T>(env);
		
		LogicExpression<T> hasHeardMsg = new OrExpression<T>(hasHeardRequest, hasHeardAck);
		LogicExpression<T> isPassee = new AndExpression<T>(hasHeardMsg, ballTooFar);
		
		
		LogicExpression<T> isNotPassee = new NotExpression<T>(isPassee); 
		
		LogicExpression<T> shouldScout = new AndExpression<T>(hasNotScouted, isNotPassee); 

		LogicExpression<T> shouldGoToBall = new AndExpression<T>(new AndExpression<T>(hasScouted, ballTooFar), new NotExpression<T>( isPassee)); 
		
		LogicExpression<T> shouldGoHomeA = new AndExpression<T>(hasScouted, isNotPassee);
		LogicExpression<T> shouldGoHomeB = new AndExpression<T>(shouldGoHomeA, ballNotTooFar);
		LogicExpression<T> shouldGoHome = new AndExpression<T>(shouldGoHomeB, notAtHome);
		
		addTransition(scout, passee, isPassee);
		addTransition(scout, goBack, shouldGoHome);
		addTransition(scout, gotoBall, shouldGoToBall);

		addTransition(goBack, gotoBall, shouldGoToBall);
		addTransition(goBack, passee, isPassee);
		addTransition(goBack, scout, shouldScout);
		
		addTransition(gotoBall, passee, isPassee);
		addTransition(gotoBall, scout, shouldScout);
		addTransition(gotoBall, goBack, shouldGoHome);
		
		addTransition(passee, goBack, shouldGoHome);		
		addTransition(passee, gotoBall, shouldGoToBall);
		addTransition(passee, scout, shouldScout);
	}
}