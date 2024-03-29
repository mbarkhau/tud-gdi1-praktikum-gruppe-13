package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI.OffensiveAI;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GoToStartPosition;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Scout;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.GameIsOn;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.KickOffOwn;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.Timer;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class SuperAI<T extends GameEnv> extends BaseHfsm<T> {

	public SuperAI(FieldPlayer<T> player) throws AutomatonException {
		super(player);

		int number = player.getNumber();

		StudentHFSM<T> defense = new DefenseAI<T>(player);
		StudentHFSM<T> offense = new OffensiveAI<T>(player);
		StudentHFSM<T> goalie = new GoalieAi<T>(player);
		StudentHFSM<T> scout = new Scout<T>(player);
		StudentHFSM<T> backToStart = new GoToStartPosition<T>(player);
		StudentHFSM<T> kickOff = new GoalKick<T>(player);
		
		addState(defense);
		addState(offense);
		addState(backToStart);
		addState(goalie);
		addState(scout);
		addState(kickOff);
		
		setInitialState(backToStart);
		
		LogicExpression<T> gameOn = new GameIsOn<T>(env);
		LogicExpression<T> gameNotOn = new NotExpression<T>(gameOn);
		LogicExpression<T> ourPlay = new KickOffOwn<T>(env);
		LogicExpression<T> waitForReposition = new Timer<T>(env, 3);

		if (number == 1) {
			addTransition(backToStart, kickOff, ourPlay);
			addTransition(kickOff, goalie, gameOn);
			addTransition(goalie, backToStart, gameNotOn);
			addTransition(backToStart, goalie, gameOn);
		}else if (number <9) {
			addTransition(backToStart, defense, gameOn);
			addTransition(defense, backToStart, gameNotOn);
		} 
		
		else {
			addTransition(offense, backToStart, gameNotOn);
			addTransition(backToStart, scout, waitForReposition);
			addTransition(scout, offense, gameOn);
			backToStart.doOutput();
		}
	}
}