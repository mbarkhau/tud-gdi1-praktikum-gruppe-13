package mis.gdi1lab07.student;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMHandler;
import mis.gdi1lab07.automaton.HFSMTransition;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.student.gameData.FlagConstants;
import utilities.LogLevel;
import utilities.Logger;

/**
 * This class must be implemented by students.
 */
public class StudentHFSM<T> implements HFSM<T>, FlagConstants {

	private String name;

	private HFSM<T> stateHFSM;

	private StudentHFSM<T> initialState;

	private Map<String, StudentHFSM<T>> states = new HashMap<String, StudentHFSM<T>>();

	private List<HFSMTransition<T>> transitions = new LinkedList<HFSMTransition<T>>();

	private Logger log;

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentHFSM MUST be fully functional after instantiation
	 * with this constructor!
	 */
	public StudentHFSM() {
	}

	@Override
	public void doOutput() throws AutomatonException {
		if (log == null)
			System.out.println(name);
		else
			log.log(LogLevel.Info, name);
	}

	@Override
	public HFSM<T> input(T context) throws AutomatonException {
		HFSM<T> nextState = null;
		for (HFSMTransition<T> trans : transitions) {
			if (trans.getStartState() == stateHFSM && trans.eval(context)) {
				if (nextState != null){
					String errMsg = "Multiple transitions for this input.\n";
					errMsg += "Transition: " + trans.getStartState() + " -> " + trans.getTargetState();
					throw new AutomatonException(errMsg);
				}
				nextState = trans.getTargetState();

			}
		}
		if (nextState != null) { // Eine feuernde Transition
			nextState.reset();
			log.log(LogLevel.Info, "Transition: " + stateHFSM + " -> " + nextState);
			System.out.println("Transition: " + stateHFSM + " -> " + nextState);
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

	/*
	 * @Override public void serialize(HFSMHandler<T> handler) throws
	 * AutomatonException { if (hasStates()) { handler.beginState(name, true);
	 * doSerialization(handler); handler.endState(); } else handler.state(name,
	 * true); }
	 * 
	 * public void doSerialization(HFSMHandler<T> handler) throws
	 * AutomatonException { for (StudentHFSM<T> curState : states.values()) {
	 * boolean isInitial = (curState == initialState); if (curState.hasStates()) {
	 * handler.beginState(curState.getName(), isInitial);
	 * curState.doSerialization(handler); handler.endState();
	 * serializeTransitions(handler, curState); } else {
	 * handler.state(curState.getName(), isInitial);
	 * serializeTransitions(handler, curState); } } }
	 */

	public void serializeTransitions(HFSMHandler<T> handler, HFSM<T> startState)
			throws AutomatonException {
		for (HFSMTransition<T> curTrans : transitions) {
			if (curTrans.getStartState() == startState)
				handler.transition(curTrans.getStartState().toString(),
						curTrans.getTargetState().toString(), curTrans
								.getName(), curTrans.getExp());
		}
	}

	@Override
	public void serialize(HFSMHandler<T> handler) throws AutomatonException {
		// Wird aufgerufen f�r den �u�ersten Automat. Dieser kann keine
		// Transitionen haben.
		handler.beginState(this.name, true);
		doSerialize(handler);
		handler.endState();

		// Es wird ein Zustand mehr geschlossen als ge�ffnet, dies signalisiert
		// dem Handler,
		// dass die Serialisierung abgeschlossen ist
		// handler.endState();

	}

	public void doSerialize(HFSMHandler<T> handler) throws AutomatonException {
		// Serialisiert die inneren Zust�nde eines HFSM
		// Dazu wird jeder Zustand durchgegangen, und wenn dieser Kindzust�nde
		// hat,
		// beginState aufgerufen, sonst wird mittels State �bergeben.
		// beginState
		// serialisiere unterzustand
		// end State
		// transitionen
		// oder
		// state
		// transitionen

		for (StudentHFSM<T> current : states.values()) {
			if (current.hasStates()) {
				handler
						.beginState(current.getName(),
								(initialState == current));
				current.doSerialize(handler);
				handler.endState();
				serializeTransitions(handler, current);
			} else {
				handler.state(current.getName(), (initialState == current));
				serializeTransitions(handler, current);
			}

		}

	}

	// public void serializeTransitions(HFSMHandler<T> handler) throws
	// AutomatonException {
	// for (HFSMTransition<T> trans : transitions) {
	// handler.transition(this.name, trans.getTargetState().toString(),
	// trans.getName(), trans.getExp());
	// }
	// }

	@Override
	public void setLog(Logger log) {
		this.log = log;
		for (HFSM<T> state : states.values()) {
			state.setLog(log);
		}
	}

	public String getName() {
		return (name != null) ? name : getClass().getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentHFSM<T> getInitialState() {
		return initialState;
	}

	public void setInitialState(StudentHFSM<T> initialState) {
		this.initialState = initialState;
	}

	
	public void addTransition(String startState, String targetState,
			String transitionName, LogicExpression<T> exp)
			throws AutomatonException {

		if (!states.containsKey(targetState))
			throw new AutomatonException(
					"Couldn't create transition, missing targetState "
							+ targetState);
		if (!states.containsKey(startState))
			throw new AutomatonException(
					"Couldn't create transition, missing startState "
							+ startState);

		addTransition(states.get(startState), states.get(targetState),
				transitionName, exp);
	}

	/** Shorthand, uses exp.toString() as name for Transition. */
	public void addTransition(StudentHFSM<T> startState,
			StudentHFSM<T> targetState, LogicExpression<T> exp)
			throws AutomatonException {
		addTransition(startState, targetState, exp.toString(), exp);
	}
	
	public void addTransition(StudentHFSM<T> startState,
			StudentHFSM<T> targetState, String transitionName,
			LogicExpression<T> exp) throws AutomatonException {
		
		if (!states.containsValue(targetState))
			throw new AutomatonException(
					"Couldn't create transition, missing targetState "
							+ targetState);

		if (!states.containsValue(startState))
			throw new AutomatonException(
					"Couldn't create transition, missing startState "
							+ startState);

		HFSMTransition<T> newTrans = new HFSMTransition<T>();

		newTrans.setExp(exp);
		newTrans.setName(transitionName);
		newTrans.setStartState(startState);
		newTrans.setTargetState(targetState);
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
