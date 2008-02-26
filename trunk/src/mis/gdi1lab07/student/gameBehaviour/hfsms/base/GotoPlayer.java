package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import java.util.Collection;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;

/** Goto a specified player */
public class GotoPlayer<T extends GameEnv> extends BaseHfsm<T> {

	private boolean ownTeam;

	private int playerId;

	private int power = POWER_RUN;

	/**
	 * @param playerId
	 *            If the player is not found, the closest player of the selected
	 *            team is followed.
	 */
	public GotoPlayer(FieldPlayer<T> player, boolean ownTeam, int playerId) {
		super(player);
		this.ownTeam = ownTeam;
		this.playerId = playerId;
	}
	

	@Override
	public void doOutput() throws AutomatonException {

		if(Utils.debugThis(Utils.DBG_ALL))
		System.out.println(this.getName());
		FieldVector p = (ownTeam) ? env.getOwnPlayer(playerId) : env
				.getOtherPlayer(playerId);

		if (p == null) {
			// finde den nächsten spieler
			Collection<FieldVector> players = (ownTeam) ? env.getOwnPlayers()
					: env.getOtherPlayers();
			for (FieldVector current : players)
				if (p == null
						|| current.getDist() < p.getDist())
					p = current;
		}
		
		gotoVector(p, power, DELTA_DYNAMIC);
	}

	/**
	 * Set the power with which dashes should be made <br>
	 * default: {@link BaseHfsm#POWER_RUN}
	 */
	public void setPower(int power) {
		this.power = power;
	}
}
