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

	private HFSM<T> initialState;

	private Map<String, HFSM<T>> states = new HashMap<String, HFSM<T>>();

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
		System.out.println(name);
	}

	@Override
	public HFSM<T> input(T context) throws AutomatonException {
		HFSM<T> nextState = null;
		for (HFSMTransition<T> trans : transitions) {
			if (trans.eval(context)) {
				if (nextState != null)
					throw new AutomatonException(
							"Multiple transitions for this input.");

				nextState = trans.getTargetState();

			}
		}
		if (nextState != null) {
			stateHFSM = nextState;
			return nextState;
		}
		stateHFSM.input(context);
		doOutput();
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
	}

	@Override
	public void serialize(HFSMHandler<T> handler) throws AutomatonException {
		// TODO Auto-generated method stub
	}

	@Override
	public void setLog(Logger log) {
		this.log = log;
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

	public void setInitialState(HFSM<T> initialState) {
		this.initialState = initialState;
	}

	public void addTransition(String startState, String targetState,
			String transitionName, LogicExpression<T> exp)
			throws AutomatonException {
		HFSMTransition<T> newTrans = new HFSMTransition<T>();
		
		if (!states.containsKey(targetState))
			throw new AutomatonException("Couldn't create transition, missing targetState " + targetState);
		if (!states.containsKey(startState))
			throw new AutomatonException("Couldn't create transition, missing startState " + startState);
		
		newTrans.setExp(exp);
		newTrans.setName(transitionName);
		newTrans.setStartState(states.get(startState));
		newTrans.setTargetState(states.get(targetState));
		transitions.add(newTrans);
	}

	public void addState(StudentHFSM<T> state) {
		states.put(state.getName(), state);
	}
	
	public boolean hasStates(){
		return states.size() > 0;
	}
	
	public String toString(){
		return name;
	}
}
