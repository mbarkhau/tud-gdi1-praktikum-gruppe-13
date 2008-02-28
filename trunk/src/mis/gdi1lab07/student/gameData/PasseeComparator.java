package mis.gdi1lab07.student.gameData;

import java.util.Comparator;

public class PasseeComparator<T extends PlayerVector> implements Comparator<T> {

	double GOAL_DIST_WEIGHT = 10;
	
	double ENEMY_DIST_WEIGHT = 5;
	
	@Override
	public int compare(T p1, T p2) {
		if (p1.isEnemyBetween() && !p2.isEnemyBetween())
			return 1;
		if (!p1.isEnemyBetween() && p2.isEnemyBetween())
			return -1;
		
		//lower is better
		double p1Score = (p1.getDistToGoal() * GOAL_DIST_WEIGHT) + (p1.getDistToClosestEnemy() * ENEMY_DIST_WEIGHT);
		double p2Score = (p2.getDistToGoal() * GOAL_DIST_WEIGHT) + (p2.getDistToClosestEnemy() * ENEMY_DIST_WEIGHT);

		if(Utils.debugThis(Utils.DBG_ALL))
		System.out.println(p1 + " vs " + p2 + ": " + (p2Score - p1Score));
		return new Double(p2Score - p1Score).intValue();
	}

}
