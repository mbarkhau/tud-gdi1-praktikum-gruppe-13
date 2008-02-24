package mis.gdi1lab07.student.gameBehaviour.hfsms;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/** @deprecated */
public class FollowEnemyPlayer<T extends GameEnv> extends StudentHFSM<T> {
	

	private FieldPlayer<T> player;
	
	public FollowEnemyPlayer(FieldPlayer<T> player) {
		super();
		this.player = player;
	}
	
	private void turnToNearestPlayer() {
		FieldVector nearestPlayer = null;
		for (FieldVector current : player.getEnv().getOtherPlayers()) {
			//Bestimme den am n�hesten befindlichen Spieler
			if(nearestPlayer==null)
				nearestPlayer = current;
			else if(current.getDistance()<nearestPlayer.getDistance()) {
				nearestPlayer = current;
			}
		}
		player.turn(nearestPlayer.getDirection());
	}
	
	public void doOutput() throws AutomatonException {
		turnToNearestPlayer();
		player.dash(50);
	}

}
