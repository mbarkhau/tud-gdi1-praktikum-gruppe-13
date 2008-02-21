package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.AssocNumberPosition;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.Utils;

public class GoToStartPosition<T> extends StudentHFSM<T> {

	private FieldPlayer player;
	
	private int number;
	
	public GoToStartPosition(FieldPlayer player) {
		super();
		this.player = player;
		this.number = player.getNumber();
	}
	
	public void doOutput() throws AutomatonException {
		FieldVector flagVector = player.getEnv().getFlag(AssocNumberPosition.getPositionFlag(player.getNumber()));
		if (flagVector != null){
			if (!Utils.isDirectionEqual(flagVector.getDirection(), 0))
				player.turn(flagVector.getDirection());
			player.dash(60);
		}
	}
	
}
