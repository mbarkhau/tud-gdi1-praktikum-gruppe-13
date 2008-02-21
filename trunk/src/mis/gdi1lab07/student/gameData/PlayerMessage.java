package mis.gdi1lab07.student.gameData;

public class PlayerMessage {

	private int playerId;
	
	private String msg;
	
	private boolean ownTeam;
	
	public PlayerMessage(int playerId, String msg, boolean ownTeam) {
		this.playerId = playerId;
		this.msg = msg;
		this.ownTeam = ownTeam;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isOwnTeam() {
		return ownTeam;
	}
	
}
