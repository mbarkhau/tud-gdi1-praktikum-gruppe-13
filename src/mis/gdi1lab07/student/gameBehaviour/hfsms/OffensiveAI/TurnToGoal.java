package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;

public class TurnToGoal<T extends GameEnv> extends BaseHfsm<T> {

	public TurnToGoal(FieldPlayer<T> player) {
		super(player);
	}

	@Override
	public void doOutput() throws AutomatonException {
		FieldVector goaly = this.player.getEnv().getFlag(FlagConstants.T_G_C);
		if(goaly==null){
			this.player.turn(90);
		}
		else{
			this.player.turn(goaly.getDirection());
		}
	}

	
}
