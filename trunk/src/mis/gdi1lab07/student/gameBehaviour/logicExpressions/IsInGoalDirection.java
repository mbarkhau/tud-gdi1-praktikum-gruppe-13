package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.base.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.FlagConstants;
import mis.gdi1lab07.student.gameData.GameEnv;

public class IsInGoalDirection<T extends GameEnv> extends BaseLogicExpression<T> {
	
	public IsInGoalDirection(T env) {
		super(env);
	}
	
	@Override
	public boolean eval(T env) throws LogExpException {
		
		if(env.getFlag(FlagConstants.T_G_C)!=null){
			if(env.getFlag(FlagConstants.T_G_C).getDirection()<Math.abs(1.5)){
				return true;
			}
		}
		return false;
	}

}
