package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameBehaviour.hfsms.base.BaseHfsm;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;

/**
 * L�sst player in Richtung gegnerisches Tor dribbeln / bzw. leicht nach vorne
 * schiessen
 */
public class DribbleOnGoal<T extends GameEnv> extends BaseHfsm<T> {

	public DribbleOnGoal(FieldPlayer<T> player) {
		super(player);
	}

	@Override
	public void doOutput() throws AutomatonException {
		for (FieldVector s : env.getOtherPlayers()){
		  if (s!=null)
			if (s.getDist() < 3 && Math.abs(s.getDir()) < 20){
				this.player.kick(40, 25);
				return;
			}
		}
		if(env.getFlag(T_G_C)!=null)
			this.player.kick(20, env.getFlag(T_G_C).getDir());		
		else
			player.turn(90);
	}
}