package mis.gdi1lab07.student.gameData;

public class PlayerMessage {

	private final int playerId;
	
	private final String msg;
	
	
	private final int tick;
	
	public PlayerMessage(int playerId, String msg, int tick) {
		this.playerId = (playerId != -1) ? playerId : Utils.findPlayerId(msg);
		this.msg = msg;
		this.tick = tick;
		if(Utils.debugThis(Utils.DBG_ALL))
		System.out.println(this);
	}
	
	public int getPlayerId() {
		return playerId;
	}

	public String getMsg() {
		return msg;
	}

	public boolean isOwnTeam() {
		return Utils.findPlayerId(msg) != -1;
	}
	
	public String toString(){
		return tick + "  " + playerId + " said " + msg;
	}

	public int getTick() {
		return tick;
	}

}
