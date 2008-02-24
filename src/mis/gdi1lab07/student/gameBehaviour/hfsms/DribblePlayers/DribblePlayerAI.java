package mis.gdi1lab07.student.gameBehaviour.hfsms.DribblePlayers;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.WalkToBall;
import mis.gdi1lab07.student.gameBehaviour.hfsms.OffensivePlayers.LookAhead;
import mis.gdi1lab07.student.gameBehaviour.hfsms.OffensivePlayers.ScoutForBall;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BallVisible;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

/** DribblePlayer (siehe Aufg. 5.1.3, 
 * analog zu DribblePlayer aus Aufg. 1, jetzt aber als HFSM)
 */
public class DribblePlayerAI<T extends GameEnv> extends StudentHFSM<T>{

	public DribblePlayerAI(FieldPlayer player) throws AutomatonException{

		// Anfangszustand vom Dribbler
		StudentHFSM<T> lookAhead = new LookAhead<T>(player);
		
		// schaut sich nach Ball um
		StudentHFSM<T> scoutForBall = new ScoutForBall<T>(player);
		
		// l�uft zum Ball
		StudentHFSM<T> walkToBall = new WalkToBall<T>(player);
		
		//dribbelt aufs Tor
		StudentHFSM<T> dribbleOnGoal = new DribbleOnGoal<T>(player);
		
		// schiesst aufs Tor
		StudentHFSM<T> shoot = new Shoot<T>(player);
		
		setInitialState(lookAhead);
		
		addState(lookAhead);
		addState(scoutForBall);
		addState(walkToBall);
		addState(dribbleOnGoal);
		addState(shoot);
		
		
		//Transitionen:
		// "lookAhead" <ich sehe Ball>  "walkToBall"
		addTransition(lookAhead.getName(), walkToBall.getName(), "i see ball", new BallVisible((T) player.getEnv()));
		
		// "lookAhead" <ich sehe Ball nicht> "scoutForBall"
		NotExpression seeBallNOT = new NotExpression(new BallVisible((T) player.getEnv()));
		addTransition(lookAhead.getName(), scoutForBall.getName(), "i can't see ball", seeBallNOT);
		
		// "scout" <hasScoutedBall> "walkToBall"
		
		
		// "walkToBall" <ich bin am Ball> "dribbleOnGoal"
		
		// "dribbleOnGoal" <Entfernung zum Tor <= X> "shoot"
		
		// "dribbleOnGoal" <ich bin nicht am Ball> "DribblePlayer verlassen"
		
		
		// "lookAhead" <ich h�re PassAnfrage> "antworte positiv"
		
		// "walkToBall" <ich h�re PassAnfrage> "antworte positiv"
	}
	

}