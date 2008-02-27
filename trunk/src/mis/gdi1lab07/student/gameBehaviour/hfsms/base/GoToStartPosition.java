package mis.gdi1lab07.student.gameBehaviour.hfsms.base;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldVector;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.Utils;



public class GoToStartPosition<T extends GameEnv> extends BaseHfsm<T> implements Formations{

	private int power = POWER_RUN;
	
	String formation;
	
	public GoToStartPosition(FieldPlayer<T> player) {
		super(player);
	}
	
	/**
	 * If the player can use the move command, she moves randomly otherwise dash
	 * to the flag.
	 */
	public void doOutput() throws AutomatonException {
		if (Utils.canUseMove(env.getPlayMode())) {
			player.move(getX(player.getNumber()), getY(player.getNumber()));
		} else {
			FieldVector f = env.getFlag(Utils.getPlayerPos(player.getNumber()));
			gotoVector(f, power, DELTA_STATIC);
		}
	}
	
	/**
	 * Set the power with which dashes should be made <br>
	 * default: {@link BaseHfsm#POWER_WALK}
	 */
	public void setPower(int power) {
		this.power = power;
	}
	
	public void setFormation(String formation){
		this.formation = formation;
	}
	
	public int getX(int number){
		if(formation==null){
			return x433[number];
		}
		return x433[number];
	}
	
	public int getY(int number){
		if(formation==null){
			return y433[number];
		}
		return y433[number];
	}
}
