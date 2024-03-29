package mis.gdi1lab07.student.gameData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameEnv {

	//SEHR delikate einstellung, unbedingt mit joggerai testen
	private double STAMINA_POW_DEPLETE_FAKTOR = 0.98;
	
	private double STAMINA_REGEN_FAKTOR = 39.5;
	
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

	private double stamina = 4000;
	
	private HashMap<Integer, Object> hfsmParams = new HashMap<Integer, Object>();

	public Object getHfsmParam(Integer key) {
		return hfsmParams.get(key);
	}

	public void setHfsmParam(Integer key, Object value) {
		hfsmParams.put(key, value);
	}

	public FieldVector getBall() {
		return (ball != null && ball.getAge() < 5) ? ball : null;
	}

	public FieldVector getOwnPlayer(int id) {
		FieldVector p = ownPlayers.get(id);
		return (p != null && p.getAge() < 10) ? p : null;
	}

	public FieldVector getOtherPlayer(int id) {
		FieldVector p = otherPlayers.get(id);
		return (p != null && p.getAge() < 10) ? p : null;
	}
	
	public FieldVector getPlayer(boolean ownTeam, int playerId){
		return (ownTeam) ? ownPlayers.get(playerId) : otherPlayers.get(playerId);
	}

	public int getClosestPlayerId(boolean ownTeam){
		Map<Integer, FieldVector> selectedPlayers = (ownTeam) ? ownPlayers : otherPlayers;
		FieldVector closestVec = null;
		Integer closestKey = null;
		for (Integer pKey : selectedPlayers.keySet()) {
			FieldVector curVec = selectedPlayers.get(pKey);
			if (closestKey == null || curVec.getDist() < closestVec.getDist()){
				closestKey = pKey;
				closestVec = curVec;
			}	
		}
		return closestKey.intValue();
	}
	
	public FieldVector getClosestPlayer(boolean ownTeam){
		return getPlayer(ownTeam, getClosestPlayerId(ownTeam));
	}
	
	public FieldVector getFlag(int id) {
		FieldVector f = flags.get(id);
		if (Utils.getDebugLevel() > Utils.DBG_FLAGS)
			System.out.println(playerId + " flag " + id + " at "+ f.toString());
		return (f != null && f.getAge() < 25) ? f : null;
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
		stamina -= power * STAMINA_POW_DEPLETE_FAKTOR;
		if (stamina < 0)
			stamina = 0;
		if (Utils.getDebugLevel() > Utils.DBG_STAMINA)
			System.out.println(playerId + " stamina: " + stamina);
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
	
	public FieldVector getHomeFlag() {
		return flags.get(getHomePos());
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
	 * Finde eine nachricht eines eigenen spielers, der am weitesten forne ist
	 * und bei dem möglichst kein gegner im weg ist.
	 * 
	 * @return die spielerid des sprechers oder -1, falls keiner die nachricht
	 *         gesagt hat.
	 */
	public int findSpeaker(String msg) {
		List<PlayerVector> availablePlayers = new ArrayList<PlayerVector>();
		for (Integer key : ownPlayers.keySet()) {
			//hat spieler msg gesagt
			if (gotMsgFromPlayer(msg, key)){
				availablePlayers.add(new PlayerVector(this, key, ownPlayers.get(key)));
			}
		}
		
		if (availablePlayers.isEmpty()){
			return -1;
		} else {
			Collections.sort(availablePlayers, new PasseeComparator<PlayerVector>());
			return availablePlayers.get(0).getPlayerId();
		}
		
	}

	public boolean gotMsgFromPlayer(String msg, int speakerId) {
		for (PlayerMessage curMsg : msgs) {
			if (((tick - curMsg.getTick()) < 5)
					&& curMsg.getMsg().startsWith(msg)
					&& curMsg.getPlayerId() == speakerId) {
				return true;
			}
		}
		return false;
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
		
		stamina += STAMINA_REGEN_FAKTOR;
		if (stamina > 4000)
			stamina = 4000;
		if (Utils.getDebugLevel() > Utils.DBG_STAMINA)
			System.out.println(playerId + " stamina: " + stamina);
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

	public double getStamina() {
		return stamina;
	}
}
