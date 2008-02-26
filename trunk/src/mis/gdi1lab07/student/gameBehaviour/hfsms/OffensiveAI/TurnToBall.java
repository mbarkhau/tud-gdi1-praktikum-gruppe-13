package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;

public class TurnToBall<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public TurnToBall(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
		
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		FieldVector bally = null;
		System.out.print(player.getNumber());
		if(this.player.getEnv().getBall().getAge()<3){
			bally=player.getEnv().getBall();
		}
		if(bally==null){
			System.out.println(": TURN (TO BALL) 90!");
			this.player.turn(90);
		}
		else{
			System.out.println(" TURN TO BALL "+bally.getDir()+"");
			this.player.turn(bally.getDir());
		}
	}

}
