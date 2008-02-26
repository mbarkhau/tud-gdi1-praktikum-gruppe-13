package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;
/**
 * L�sst player aufs Tor schiessen
 */
public class Shoot<T> extends StudentHFSM<T> implements FlagConstants{

	private final FieldPlayer player;

	public Shoot(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	public void doOutput() throws AutomatonException{
		System.out.println(this.player.getNumber()+" shoots (kick 100)");
		double playerTurn = 0.7+0.2*Math.random(); // Zahl zw. 0.0 und 1.0
		if(Math.random()<0.5){
			playerTurn *= player.getEnv().getFlag(T_G_L).getDir();
		}
		else{
			playerTurn *= player.getEnv().getFlag(T_G_R).getDir();
		}
		for (FieldVector p : player.getEnv().getOtherPlayers()) {
			if(Math.abs(p.getDir()-playerTurn)<0.5){
				playerTurn = -playerTurn;
				break;
			}
		}
		player.turn(playerTurn);
		System.out.println("PLAYER TURN RANDOM="+playerTurn);
		player.kick(100,0);
	}

}