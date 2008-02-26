package mis.gdi1lab07.student.gameData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameEnv {

	private FieldVector ball = null;

	// Key is the player id
	private Map<Integer, FieldVector> ownPlayers = new HashMap<Integer, FieldVector>();

	// Key is the player id
	private Map<Integer, FieldVector> otherPlayers = new HashMap<Integer, FieldVector>();

	// Key is the flag id,
	private Map<Integer, FieldVector> flags = new HashMap<Integer, FieldVector>();

	private List<PlayerMessage> msgs = new ArrayList<PlayerMessage>();

	private int gameMode = 0;

	private int playerId = 0;

	private int tick = 0;

	private HashMap<Integer, Object> hfsmParams = new HashMap<Integer, Object>();

	public Object getHfsmParam(Integer key) {
		return hfsmParams.get(key);
	}

	public void setHfsmParam(Integer key, Object value) {
		hfsmParams.put(key, value);
	}

	public FieldVector getBall() {
		return (ball != null && ball.getAge() < 3) ? ball : null;
	}

	public FieldVector getOwnPlayer(int id) {
		FieldVector p = ownPlayers.get(id);
		return (p != null && p.getAge() < 5) ? p : null;
	}

	public FieldVector getOtherPlayer(int id) {
		FieldVector p = otherPlayers.get(id);
		return (p != null && p.getAge() < 5) ? p : null;
	}

	public FieldVector getFlag(int id) {
		FieldVector f = flags.get(id);
		return (f != null && f.getAge() < 15) ? f : null;
	}

	public Collection<FieldVector> getOwnPlayers() {
		return ownPlayers.values();
	}

	public Collection<FieldVector> getOtherPlayers() {
		return otherPlayers.values();
	}

	public Collection<FieldVector> getFlags() {
		return flags.values();
	}

	/**
	 * @param angle
	 *            mit dem sich der spieler gedreht.
	 */
	public void turn(double angle) {
		List<FieldVector> vectors = new LinkedList<FieldVector>();
		if (ball != null)
			vectors.add(ball);
		vectors.addAll(ownPlayers.values());
		vectors.addAll(otherPlayers.values());
		vectors.addAll(flags.values());

		for (FieldVector v : vectors) {
			Utils.turnVector(angle, v);
		}
	}

	/**
	 * @param pow
	 *            mit dem sich der spieler bewegt.
	 */
	public void dash(int power) {
		List<FieldVector> vectors = new LinkedList<FieldVector>();
		if (ball != null)
			vectors.add(ball);
		vectors.addAll(ownPlayers.values());
		vectors.addAll(otherPlayers.values());
		vectors.addAll(flags.values());
		for (FieldVector v : vectors) {
			Utils.displaceVector(power, v);
			v.doAge();
		}
	}

	public void setFlag(int id, double dist, double dir) {
		FieldVector flag = new FieldVector(dist, dir);
		flags.put(id, flag);
	}

	public void setOwnPlayer(int id, double dist, double dir) {
		FieldVector player = new FieldVector(dist, dir);
		ownPlayers.put(id, player);
	}

	public void setOtherPlayer(int id, double dist, double dir) {
		FieldVector player = new FieldVector(dist, dir);
		otherPlayers.put(id, player);
	}

	public void setBall(FieldVector ball) {
		this.ball = ball;
	}

	public int getPlayMode() {
		return gameMode;
	}

	public void setPlayMode(int gameMode) {
		this.gameMode = gameMode;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getHomePos() {
		return Utils.getPlayerPos(playerId);
	}

	public void addMsg(double dir, String msg) {
		int speakerId = -1;
		for (Integer curId : ownPlayers.keySet()) {
			if (Utils.inDelta(dir, ownPlayers.get(curId).getDir())) {
				speakerId = curId;
			}
		}
		PlayerMessage newMsg = new PlayerMessage(speakerId, msg, tick);

		msgs.add(newMsg);
	}

	/**
	 * Finde eine nachricht eines eigenen spielers, der am weitesten forne ist und
	 * 
	 * @return die spielerid des sprechers oder -1, falls keiner die nachricht
	 *         gesagt hat.
	 */
	public int findSpeaker(String msg) {
		
		
		for (PlayerMessage curMsg : msgs) {
			if (curMsg.getMsg().startsWith(msg) && curMsg.isOwnTeam()
					&& curMsg.getPlayerId() != -1) {
				return curMsg.getPlayerId();
			}
		}
		return -1;
	}

	public boolean receivedMessage(String msg) {
		for (PlayerMessage currentMsg : msgs) {
			if (((tick - currentMsg.getTick()) < 5)
					&& currentMsg.getMsg().startsWith(msg)) {
				return true;
			}
		}
		return false;
	}

	public void removeMessage(String msg) {
		PlayerMessage playerMsg = null;
		for (PlayerMessage currentMsg : msgs) {
			if (currentMsg.getMsg().equals(msg)) {
				playerMsg = currentMsg;
			}
		}
		msgs.remove(playerMsg);
	}

	/** Age all non static vectors */
	public void doAge() {
		List<FieldVector> vectors = new LinkedList<FieldVector>();
		if (ball != null)
			vectors.add(ball);
		vectors.addAll(ownPlayers.values());
		vectors.addAll(otherPlayers.values());
		for (FieldVector v : vectors) {
			v.doAge();
		}
	}

	/** remove all vectors in view and hence, should be visible on the next turn. */
	public void cleanUp() {
		// might do more harm than good, so leaving unimplemented for now

	}

	public int getTick() {
		return tick;
	}

	public void doTick() {
		tick++;
	}

	public void resetTick() {
		tick = 0;
	}
}
