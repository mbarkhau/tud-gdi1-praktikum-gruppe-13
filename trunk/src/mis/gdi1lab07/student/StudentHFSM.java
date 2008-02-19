package mis.gdi1lab07.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMHandler;
import mis.gdi1lab07.automaton.HFSMTransition;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import utilities.LogLevel;
import utilities.Logger;

/**
 * This class must be implemented by students.
 */
public class StudentHFSM<T> implements HFSM<T> {

	private String name;

	private HFSM<T> stateHFSM;

	private StudentHFSM<T> initialState;

	private Map<String, StudentHFSM<T>> states = new HashMap<String, StudentHFSM<T>>();

	private List<HFSMTransition<T>> transitions = new ArrayList<HFSMTransition<T>>();

	private Logger log;

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentHFSM MUST be fully functional after instantiation
	 * with this constructor!
	 */
	public StudentHFSM() {
		// TODO This constructor MUST be implemented correctly!!
	}

	@Override
	public void doOutput() throws AutomatonException {
		log.log(LogLevel.Info, name);
	}

	@Override
	public HFSM<T> input(T context) throws AutomatonException {
		HFSM<T> nextState = null;
		for (HFSMTransition<T> trans : transitions) {
			if (trans.getStartState() == stateHFSM && trans.eval(context)) {
				if (nextState != null)
					throw new AutomatonException(
							"Multiple transitions for this input.");

				nextState = trans.getTargetState();

			}
		}
		if (nextState != null) { // Eine feuernde Transition
			nextState.reset();
			stateHFSM = nextState;
			output();
			return nextState;
		}
		doOutput();
		if (stateHFSM != null) // Keine feuernde Transition
			stateHFSM.input(context);
		return this;
	}

	@Override
	public void output() throws AutomatonException {
		doOutput();
		if (stateHFSM != null)
			stateHFSM.output();
	}

	@Override
	public void reset() throws AutomatonException {
		stateHFSM = initialState;
		if (stateHFSM != null)
			stateHFSM.reset();
	}

	@Override
	public void serialize(HFSMHandler<T> handler) throws AutomatonException {
		if (hasStates()) {
			handler.beginState(name, true);
			doSerialization(handler);
			handler.endState();
		} else
			handler.state(name, true);

	}

	public void doSerialization(HFSMHandler<T> handler)
			throws AutomatonException {
		for (StudentHFSM<T> current : states.values()) {
			boolean isInitial = (current == initialState);
			if (current.hasStates()) {
				handler.beginState(current.getName(), isInitial);
				current.doSerialization(handler);
				handler.endState();
			} else {
				handler.state(current.getName(), isInitial);
			}
		}
		serializeTransitions(handler);
	}

	public void serializeTransitions(HFSMHandler<T> handler)
			throws AutomatonException {
		for (HFSMTransition<T> current : transitions) {
			handler.transition(current.getStartState().toString(), current
					.getTargetState().toString(), current.getName(), current
					.getExp());
		}

	}

	@Override
	public void setLog(Logger log) {
		this.log = log;
		for (HFSM<T> state : states.values()) {
			state.setLog(log);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HFSM<T> getInitialState() {
		return initialState;
	}

	public void setInitialState(StudentHFSM<T> initialState) {
		this.initialState = initialState;
	}

	public void addTransition(String startState, String targetState,
			String transitionName, LogicExpression<T> exp)
			throws AutomatonException {
		HFSMTransition<T> newTrans = new HFSMTransition<T>();

		if (!states.containsKey(targetState))
			throw new AutomatonException(
					"Couldn't create transition, missing targetState "
							+ targetState);
		if (!states.containsKey(startState))
			throw new AutomatonException(
					"Couldn't create transition, missing startState "
							+ startState);

		newTrans.setExp(exp);
		newTrans.setName(transitionName);
		newTrans.setStartState(states.get(startState));
		newTrans.setTargetState(states.get(targetState));
		transitions.add(newTrans);
	}

	public void addState(StudentHFSM<T> state) {
		states.put(state.getName(), state);
	}

	public boolean hasStates() {
		return states.size() > 0;
	}

	public String toString() {
		return name;
	}
}
