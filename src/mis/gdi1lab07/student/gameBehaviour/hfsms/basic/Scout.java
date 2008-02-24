package mis.gdi1lab07.student.gameBehaviour.hfsms.basic;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

public class Scout<T extends GameEnv> extends BaseHfsm<T> {
		
	public Scout(FieldPlayer<T> player){
		super(player);
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		player.turn(72);
	}
}
