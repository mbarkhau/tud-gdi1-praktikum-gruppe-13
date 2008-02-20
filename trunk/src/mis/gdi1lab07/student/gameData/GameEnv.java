package mis.gdi1lab07.student.gameData;

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
	
	private Map<Integer, String> ownMsgs = new HashMap<Integer, String>();

	private Map<Integer, String> otherMsgs = new HashMap<Integer, String>();
	
	private int gameMode = 0;
		
	public FieldVector getBall() {
		return ball;
	}

	public Collection<FieldVector> getOwnPlayers(){
		return ownPlayers.values();
	}
	
	public Collection<FieldVector> getOtherPlayers(){
		return otherPlayers.values();
	}
	
	public Collection<FieldVector> getFlags(){
		return flags.values();
	}
	
	public FieldVector getOwnPlayer(int id){
		return ownPlayers.get(id);
	}
	
	public FieldVector getOtherPlayer(int id){
		return otherPlayers.get(id);
	}
	
	public FieldVector getFlag(int id){
		return flags.get(id);
	} 
	
	/**
	 * @param angle mit dem sich der spieler gedreht.
	 */
	public void turn(double angle){
		List<FieldVector> vectors = new LinkedList<FieldVector>();
		if (ball != null)
			vectors.add(ball);
		vectors.addAll(ownPlayers.values());
		vectors.addAll(otherPlayers.values());
		vectors.addAll(flags.values());
		
		for (FieldVector v : vectors) {
			double result = v.getDirection();
			
			// Noramlisieren des Eingang-Winkels
			if(angle > 180){
				angle -= 360;
			}
			else if(angle <= -180)
				angle += 360;

			// Drehung
			result -= angle;
			
			// Normalisierung des Ausgang-Winkels
			if (result > 180)
				result -= 360;
			else if (result <= -180)
				result += 360;

			v.setDirection(result);
		}
	}

	
	public void dash(double distance){
		ball.doAge();
		flags = new HashMap<Integer, FieldVector>();
		ownPlayers = new HashMap<Integer, FieldVector>();
		otherPlayers = new HashMap<Integer, FieldVector>();
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
	
	public void addMsg(double dir, String msg){
		for (Integer playerId : ownPlayers.keySet()) {
			if (dir == ownPlayers.get(playerId).getDirection()){
				System.out.println(playerId + " said: " + msg);
				ownMsgs.put(playerId, msg);
				return;
			}
		}
		ownMsgs.put(-1, msg);
	}
}
