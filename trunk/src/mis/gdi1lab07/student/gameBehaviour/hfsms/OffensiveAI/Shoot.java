package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;
/**
 * Lässt player aufs Tor schiessen
 */
public class Shoot<T> extends StudentHFSM<T> implements FlagConstants{

	private final FieldPlayer player;

	public Shoot(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
	}
	
	public void doOutput() throws AutomatonException{
		System.out.println(this.player.getNumber()+" shoots (kick 100)");
		double playerTurnRandom = 0.8+0.15*Math.random(); // Zahl zw. 0.0 und 1.0
		if(Math.random()<0.5){
			playerTurnRandom *= player.getEnv().getFlag(T_G_L).getDirection();
		}
		else{
			playerTurnRandom *= player.getEnv().getFlag(T_G_R).getDirection();
		}
		for (FieldVector p : player.getEnv().getOtherPlayers()) {
			if(Math.abs(p.getDirection()-playerTurnRandom)<0.5){
				playerTurnRandom = -playerTurnRandom;
				break;
			}
		}
		player.turn(playerTurnRandom);
		System.out.println("PLAYER TURN RANDOM="+playerTurnRandom);
		player.kick(100,0);
	}

}