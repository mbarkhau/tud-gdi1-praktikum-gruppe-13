package mis.gdi1lab07.student.gameBehaviour.logicExpressions;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.student.gameBehaviour.logicExpressions.BaseLogicExpression;
import mis.gdi1lab07.student.gameData.GameEnv;
import mis.gdi1lab07.student.gameData.GameMessages;

/**
 * Ist der Ball JETZT GERADE im Blickfeld vom Player?
 */
public class SeeBall<T extends GameEnv> extends BaseLogicExpression<T> implements GameMessages {

	public SeeBall(T env) {
		super(env);
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		
		if(env.getBall()!=null){
			 // gibt wirklich nur dann true wenn der Ball JETZT gesehen wird
			return(env.getBall().getAge()==0);
		}
		else
			return false;
	}

}
