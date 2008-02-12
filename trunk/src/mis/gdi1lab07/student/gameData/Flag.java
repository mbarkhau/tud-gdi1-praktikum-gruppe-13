package mis.gdi1lab07.student.gameData;

import java.util.HashMap;
import java.util.Map;

import atan2.model.Controller;

/** Representation of a flag. See robocup server manual page 37 */
public class Flag {

	private final int controllerFlagType;

	private FieldPosition position;

	/** Since the types of the controler ar ambiguous, we also use are own. */
	public final Integer flagType;

	private static Map<Integer, Flag> allFlags = new HashMap<Integer, Flag>();

	public static Flag L_B = new Flag(0, Controller.FLAG_LEFT,
			new FieldPosition(0, 0));

	public static Flag L_T = new Flag(0, Controller.FLAG_RIGHT,
			new FieldPosition(0, PlayingField.HEIGHT));

	public static Flag R_B= new Flag(0, Controller.FLAG_LEFT,
			new FieldPosition(PlayingField.WIDTH, 0));

	public static Flag R_T = new Flag(0, Controller.FLAG_RIGHT,
			new FieldPosition(PlayingField.WIDTH,
					PlayingField.HEIGHT));

	public static Flag C = new Flag(0, Controller.FLAG_CENTER,
			new FieldPosition(PlayingField.WIDTH / 2,
					PlayingField.HEIGHT / 2));
	
	public static Flag C_T = new Flag(0, Controller.FLAG_LEFT,
			new FieldPosition(PlayingField.WIDTH / 2,
					PlayingField.HEIGHT));
	
	public static Flag C_B = new Flag(0, Controller.FLAG_RIGHT,
			new FieldPosition(PlayingField.WIDTH / 2,
					0));
	
	public static Flag GOAL_L = new Flag(0,	Controller.FLAG_CENTER, 
			new FieldPosition(0, PlayingField.HEIGHT / 2));

	public static Flag GOAL_R = new Flag(0,	Controller.FLAG_CENTER, 
			new FieldPosition(PlayingField.WIDTH, PlayingField.HEIGHT / 2));
	
	public static Flag FLAG_RIGHT_GOAL_CENTER = new Flag(0,
			Controller.FLAG_CENTER, new FieldPosition(
					PlayingField.WIDTH,
					PlayingField.HEIGHT / 2));

	
	public Flag getInstance(Integer flagType) {
		return allFlags.get(flagType);
	}

	private Flag(Integer flagType, int controllerFlagType,
			FieldPosition position) {
		this.flagType = flagType;
		this.controllerFlagType = controllerFlagType;
		this.position = position;
		allFlags.put(flagType, this);
	}

	public FieldPosition getPosition() {
		return position;
	}

	public Integer getFlagType() {
		return flagType;
	}

	/**
	 * @return the coorosponding Type for the Flag, used by
	 *         {@link Controller.FLAG*}
	 */
	public int getControllerFlagType() {
		return controllerFlagType;
	}

}
