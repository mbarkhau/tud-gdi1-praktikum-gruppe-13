package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

public class Scout<T extends GameEnv> extends BaseHfsm<T> {
		
	public Scout(FieldPlayer<T> player){
		super(player);
	}
	
	@Override
	public void doOutput() throws AutomatonException {

		if(Utils.debugThis(Utils.DBG_ALL))
		System.out.println(this.getName());
		player.turn(72);
	}
}
