/**
 * 
 */
package mis.gdi1lab07.student.gameData;

class PlayerVector {

	private final int playerId;
	
	private final FieldVector vector;
	
	private final GameEnv env;
	
	public PlayerVector(GameEnv env, int playerId, FieldVector vector) {
		this.playerId = playerId;
		this.vector = vector;
		this.env = env;
	}
	
	public double getDistToGoal(){
		FieldVector g = env.getFlag(FlagConstants.T_G_C);
		return Utils.getVectorDistance(vector, g);
	}
	
	public double getDistToClosestEnemy(){
		double closest = 999;
		for (FieldVector p : env.getOtherPlayers()) {
			double curDist = Utils.getVectorDistance(vector, p);
			if (curDist < closest)
				closest = curDist;
		}
		return closest;
	}
	
	public boolean isEnemyBetween(){
		for (FieldVector p : env.getOtherPlayers()) {
			if (Utils.inDelta(p.getDir(), vector.getDir())
					&& p.getDist() < vector.getDist()){
				return true;
			}
		}
		return false;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public String toString(){
		return env.getTick() + " " + playerId + " at " + vector.toString();
	}
}