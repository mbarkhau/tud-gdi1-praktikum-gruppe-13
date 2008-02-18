package mis.gdi1lab07.automaton;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;

public class HFSMTransition<T> {

	private String name;

	private HFSM<T> startState;

	private HFSM<T> targetState;

	LogicExpression<T> exp;

	public boolean eval(T env) throws AutomatonException {
		try {
			return exp.eval(env);
		} catch (LogExpException e) {
			throw new AutomatonException(e);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HFSM<T> getStartState() {
		return startState;
	}

	public void setStartState(HFSM<T> startState) {
		this.startState = startState;
	}

	public HFSM<T> getTargetState() {
		return targetState;
	}

	public void setTargetState(HFSM<T> targetState) {
		this.targetState = targetState;
	}

	public LogicExpression<T> getExp() {
		return exp;
	}

	public void setExp(LogicExpression<T> exp) {
		this.exp = exp;
	}

	public String toString() {
		return name;
	}
}
