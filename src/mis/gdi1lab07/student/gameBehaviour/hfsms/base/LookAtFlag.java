package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

public class LookAtFlag<T extends GameEnv> extends BaseHfsm<T> {

	private final int flagId;

	public LookAtFlag(FieldPlayer<T> player, int flagId) {
		super(player);
		this.flagId = flagId;
	}

	@Override
	public void doOutput() {
		FieldVector f = player.getEnv().getFlag(flagId);
		if (f == null)
			this.player.turn(90);
		else
			this.player.turn(f.getDir());
	}

}
