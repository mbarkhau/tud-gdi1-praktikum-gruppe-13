package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.GameEnv;

/** Warte. */
public class Wait<T extends GameEnv> extends BaseHfsm<T> {
	
	public Wait(FieldPlayer<T> player){
		super(player);
	}
}
