package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI.OffensiveAI;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.GotoFlag;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.Wait;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedByMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BallPassedToMe;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.HasHeardRequest;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.IsAtPosition;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.FlagInDistance;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.GameIsOn;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;


public class SuperAI<T extends GameEnv> extends StudentHFSM<T> {

	
	
	public SuperAI(FieldPlayer<T> player) throws AutomatonException{
		
		int number = player.getNumber();
		
		StudentHFSM<T> defense = new DefenseAI<T>(player);
		StudentHFSM<T> pass = new PasseeAi<T>(player);
		StudentHFSM<T> passer = new PasserAi<T>(player);
		StudentHFSM<T> offense = new OffensiveAI<T>(player);
		StudentHFSM<T> goalie = new GoalieAi<T>(player);
		StudentHFSM<T> wait = new Wait<T>(player);
		StudentHFSM<T> backToStart = new GotoFlag<T>(player, Utils.getPlayerPos(number));
		
		defense.setName("Defense");
		addState(defense);
		pass.setName("Pass annehmen");
		addState(pass);
		passer.setName("Passen");
		addState(passer);
		offense.setName("Offensive");
		addState(offense);
		wait.setName("Wait");
		addState(wait);
		backToStart.setName("Back to start");
		addState(backToStart);
		goalie.setName("Goalie");
		addState(goalie);
		
		setInitialState(backToStart);
		
		addTransition(backToStart, wait, new FlagInDistance<T>((T) player.getEnv(), Utils.getPlayerPos(number),0.5));
		
		
		if(number==1){
			addTransition(wait, goalie, new GameIsOn<T>((T) player.getEnv()));
		}
		else{
			if(number<7){
			addTransition(wait, defense, new GameIsOn<T>((T) player.getEnv()));
			}
			else{
			addTransition(wait, offense, new GameIsOn<T>((T) player.getEnv()));
			}
		}
		
		addTransition(defense, backToStart, new NotExpression<T>(new GameIsOn<T>((T) player.getEnv())));
		addTransition(offense, backToStart, new NotExpression<T>(new GameIsOn<T>((T) player.getEnv())));
		addTransition(pass, backToStart, new NotExpression<T>(new GameIsOn<T>((T) player.getEnv())));
		addTransition(passer, backToStart, new NotExpression<T>(new GameIsOn<T>((T) player.getEnv())));
		addTransition(defense, pass, new HasHeardRequest<T>((T) player.getEnv()));
		addTransition(pass, passer,new BallPassedToMe<T>((T) player.getEnv()));
		addTransition(offense, pass, new HasHeardRequest<T>((T) player.getEnv()));
		
		if(number<7)
			addTransition(passer, defense, new BallPassedByMe<T>((T) player.getEnv()));
		if(number>6)
			addTransition(passer, offense, new BallPassedByMe<T>((T) player.getEnv()));

		
		
	}
	
}
