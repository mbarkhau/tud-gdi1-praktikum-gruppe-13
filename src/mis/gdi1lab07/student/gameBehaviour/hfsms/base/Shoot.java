package mis.gdi1lab07.student.gameBehaviour.hfsms.base;


import mis.gdi1lab07.automaton.AutomatonException;
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
	
		double kickDir = 0.7 + 0.2 * Math.random(); // Zahl zw. 0.0 und 1.0
	
		// nullPointerException behoben!!!
		if (Math.random() < 0.5 && env.getFlag(T_G_L)!=null) {
				kickDir *= env.getFlag(T_G_L).getDir();
		} else {
			if(env.getFlag(T_G_R)!=null)
				kickDir *= env.getFlag(T_G_R).getDir();
			else if(env.getFlag(T_G_L)!=null)
				kickDir *= env.getFlag(T_G_L).getDir();
		}
		for (FieldVector p : env.getOtherPlayers()) {
			if (Math.abs(p.getDir() - kickDir) < 0.5) {
				kickDir = -kickDir;
				break;
			}
		}
		player.kick(100, kickDir);
	}

}