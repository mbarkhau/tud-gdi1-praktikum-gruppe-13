package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;

/**
 * Lässt player in Richtung gegnerisches Tor dribbeln / bzw. leicht nach vorne schiessen
 */
public class DribbleOnGoal<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public DribbleOnGoal(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	//TODO Player soll aufs Tor dribblen, d.h. den Ball vorlegen
	@Override
	public void doOutput() throws AutomatonException{
		boolean opponentIsInWay=false;
		for (FieldVector s : player.getEnv().getOtherPlayers()) {
			if(s.getDist()<2.5 && Math.abs(s.getDir())<5){
				opponentIsInWay=true;
				if(s.getDir()<0)
					player.turn(10);
				else
					player.turn(-10);
			}
		}
		if(!opponentIsInWay)
			this.player.kick(20, 0);
	}
}