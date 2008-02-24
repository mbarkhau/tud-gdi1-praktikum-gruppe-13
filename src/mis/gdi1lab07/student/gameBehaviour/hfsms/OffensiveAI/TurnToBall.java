package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

public class TurnToBall<T extends GameEnv> extends StudentHFSM<T> {

	private final FieldPlayer<T> player;

	public TurnToBall(FieldPlayer<T> player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		FieldVector bally = this.player.getEnv().getBall();
		if(bally==null){
			this.player.turn(90);
		}
		else{
			this.player.turn(bally.getDirection());
		}
	}

}
