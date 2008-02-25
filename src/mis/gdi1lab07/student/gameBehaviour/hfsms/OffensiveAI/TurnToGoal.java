package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;

public class TurnToGoal<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public TurnToGoal(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
		
	}

	@Override
	public void doOutput() throws AutomatonException {
		FieldVector goaly=null;
		System.out.print(player.getNumber());
		if(player.getEnv().getFlag(FlagConstants.T_G_C).getAge()<3)
			goaly = this.player.getEnv().getFlag(FlagConstants.T_G_C);
		if(goaly==null){
			this.player.turn(90);
			System.out.println(" turns (TO GOAL) 90!");
		}
		else{
			System.out.println(" turns to goal "+ goaly.getDirection());
			this.player.turn(goaly.getDirection());
			
		}
	}

	
}
