package mis.gdi1lab07.student.gameData;

public class PlayerMessage {

	private final int playerId;
	
	private final String msg;
	
	private final boolean ownTeam;
	
	private final int tick;
	
	public PlayerMessage(int playerId, String msg, boolean ownTeam, int tick) {
		this.playerId = playerId;
		this.msg = msg;
		this.ownTeam = ownTeam;
		this.tick = tick;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getMsg() {
		return msg;
	}

	public boolean isOwnTeam() {
		return ownTeam;
	}
	
	public String toString(){
		return "player "+ playerId + " said " + msg;
	}

	public int getTick() {
		return tick;
	}

}
