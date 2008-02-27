package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;
/**
 * L�sst player aufs Tor schiessen
 */
public class Shoot<T extends GameEnv> extends BaseHfsm<T> implements FlagConstants{


	public Shoot(FieldPlayer<T> player) {
		super(player);
	}
	
	public void doOutput() throws AutomatonException{
		if (Utils.debugThis(Utils.DBG_ALL))
			System.out.println(this.player.getNumber() + " shoots (kick 100)");
	
		double playerTurn = 0.7 + 0.2 * Math.random(); // Zahl zw. 0.0 und 1.0
		if (Math.random() < 0.5) {
			playerTurn *= env.getFlag(T_G_L).getDir();
		} else {
			playerTurn *= env.getFlag(T_G_R).getDir();
		}
		for (FieldVector p : env.getOtherPlayers()) {
			if (Math.abs(p.getDir() - playerTurn) < 0.5) {
				playerTurn = -playerTurn;
				break;
			}
		}
		player.turn(playerTurn);
		System.out.println("PLAYER TURN RANDOM=" + playerTurn);
		player.kick(100, 0);
	}

}