package mis.gdi1lab07.student.gameBehaviour.hfsms.OffensiveAI;


import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.Utils;

public class TurnToGoal<T> extends StudentHFSM<T> {

	private final FieldPlayer player;

	public TurnToGoal(FieldPlayer player) {
		this.player = player;
		this.setName(getClass().getName());
		
	}

	@Override
	public void doOutput() throws AutomatonException {
		FieldVector goaly=null;
		if(Utils.debugThis(Utils.DBG_ALL))
		System.out.print(player.getNumber());
		if(player.getEnv().getFlag(FlagConstants.T_G_C).getAge()<3)
			goaly = this.player.getEnv().getFlag(FlagConstants.T_G_C);
		if(goaly==null){
			this.player.turn(90);
			if(Utils.debugThis(Utils.DBG_ALL))
			System.out.println(" turns (TO GOAL) 90!");
		}
		else{
			if(Utils.debugThis(Utils.DBG_ALL))
			System.out.println(" turns to goal "+ goaly.getDir());
			this.player.turn(goaly.getDir());
			
		}
	}

	
}
