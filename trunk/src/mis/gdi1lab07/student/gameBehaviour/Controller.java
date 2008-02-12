package mis.gdi1lab07.student.gameBehaviour;

import java.util.HashMap;
import java.util.Map;

import mis.gdi1lab07.student.gameData.Ball;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldPosition;
import mis.gdi1lab07.student.gameData.Flag;
import mis.gdi1lab07.student.gameData.FlagSighting;
import mis.gdi1lab07.student.gameData.PlayingField;
import mis.gdi1lab07.student.tests.DummyPlayerImpl;
import atan2.model.ControllerAdaptor;
import atan2.model.Player;

/**
 * Central class which delegates callbacks, so FieldData is updated and requests
 * actions from implemented strategys.
 */
public class Controller extends ControllerAdaptor {

	private PlayerStrategy strategy;

	// If we made these static, we would have a common view of the game
	// for all players. Akin to telepathy.
	private PlayingField field = new PlayingField();

	private FieldPlayer fieldPlayer;

	private Ball ball = new Ball();

	private Map<Integer, FieldPlayer> players = new HashMap<Integer, FieldPlayer>();;

	public Controller(String teamName, Player player) {
		field.setOurName(teamName);
		fieldPlayer = new FieldPlayer(player);
		fieldPlayer.setTeamName(teamName);
		fieldPlayer.setPosition(0, new FieldPosition(0, 0));
	}

	// Callbacks to Controller

	@Override
	public void preInfo() {
		String result = "";
		result += "Ball: ";
		result += ball.getPosition();
		result += "My Position: ";
		result += fieldPlayer.getPosition();
		result += " - " + fieldPlayer.getViewOffset();
		result += "\n Players: ";
		for (Map.Entry<Integer, FieldPlayer> playerEntry : players.entrySet()) {
			result += playerEntry.getValue().getNumber();
			result += playerEntry.getValue().getPosition();
		}

		System.out.println(result);
	}

	@Override
	public void postInfo() {
		fieldPlayer.turn(5);
	}

	@Override
	public void infoHear(double dir, String msg) {

	}

	@Override
	public void infoHearPlayMode(int playMode) {
		field.setGameMode(playMode);
	}

	@Override
	public void infoHearReferee(int refMsg) {

	}

	@Override
	public void infoSeeBall(double dist, double dir) {
		ball.setPosition(field.getCurrentTick(), new FieldPosition(fieldPlayer
				.getPosition(), dir, dist));
	}

	@Override
	public void infoSeeFlagCenter(int id, double dist, double dir) {
		Flag flag = null;
		switch (id) {
		case FLAG_LEFT:
			flag = Flag.C_B;
			break;
		case FLAG_CENTER:
			flag = Flag.C;
			break;
		case FLAG_RIGHT:
			flag = Flag.C_T;
			break;

		default:
			break;
		}
		fieldPlayer.recordFlagSighting(field.getCurrentTick(),
				new FlagSighting(flag, dist, dir));
	}

	@Override
	public void infoSeeFlagCornerOther(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagCornerOwn(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagGoalOther(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagGoalOwn(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagLeft(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagOther(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagOwn(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagPenaltyOther(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagPenaltyOwn(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeFlagRight(int id, double dist, double dir) {

	}

	@Override
	public void infoSeeLine(int id, double dist, double dir) {

	}

	@Override
	public void infoSeePlayerOther(int id, double dist, double dir) {

	}

	@Override
	public void infoSeePlayerOwn(int id, double dist, double dir) {
		Integer playerId = new Integer(id);
		FieldPlayer seenPlayer;
		if (players.get(playerId) == null) {
			seenPlayer = new FieldPlayer(new DummyPlayerImpl(id));
			players.put(playerId, seenPlayer);
			seenPlayer.setPosition(field.getCurrentTick(), new FieldPosition(
					fieldPlayer.getPosition(), fieldPlayer.getViewOffset()
							+ dir, dist));
		} else {
			seenPlayer = players.get(playerId);

		}

	}
}
