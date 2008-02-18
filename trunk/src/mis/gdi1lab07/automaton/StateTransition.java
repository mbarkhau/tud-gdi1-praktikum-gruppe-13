package mis.gdi1lab07.automaton;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;

public class StateTransition<T> {

	private String name;
	
	private State<T> startState;
	
	private State<T> targetState;
	
	LogicExpression<T> exp;

	public boolean eval(T env) throws LogExpException{
		return exp.eval(env);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State<T> getStartState() {
		return startState;
	}

	public void setStartState(State<T> startState) {
		this.startState = startState;
	}

	public State<T> getTargetState() {
		return targetState;
	}

	public void setTargetState(State<T> targetState) {
		this.targetState = targetState;
	}

	public LogicExpression<T> getExp() {
		return exp;
	}

	public void setExp(LogicExpression<T> exp) {
		this.exp = exp;
	}
	
	public String toString(){
		return name;
	}
}
