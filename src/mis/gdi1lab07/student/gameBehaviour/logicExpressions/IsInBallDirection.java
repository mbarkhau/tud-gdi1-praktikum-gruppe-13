package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;

public class IsInBallDirection<T extends GameEnv> extends BaseLogicExpression<T> {
	
	public IsInBallDirection(T env) {
		super(env);
	}
	
	// TODO Winkel evtl noch zu ändern
	@Override
	public boolean eval(T env) throws LogExpException {
		if(env.getBall()!=null){
			return (env.getBall().getDirection()<0.5);
		}
		return false;
	}

}
