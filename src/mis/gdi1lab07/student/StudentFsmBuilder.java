package mis.gdi1lab07.student;

import java.util.HashMap;
import java.util.Map;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.FSM;
import mis.gdi1lab07.automaton.FSMBuilder;
import mis.gdi1lab07.automaton.State;
import mis.gdi1lab07.automaton.StateTransition;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import utilities.StringConsoleLogger;

/**
 * This class must be implemented by students.
 */
public class StudentFsmBuilder<T> implements FSMBuilder<T> {
	
	private FSM<T> fsm;
	
	private Map<String, State<T>> states = new HashMap<String, State<T>>();
	
	private State<T> initialState;
	
	private boolean hasBegun = false;
	
	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentFsmBuilder MUST be fully functional after
	 * instantiation with this constructor!
	 */
	public StudentFsmBuilder() {
		//TODO This constructor MUST be implemented correctly!!
		// ACH ECHT?!
	}

	@Override
	public void beginFSM() throws AutomatonException {
		//fsm = new StudentFSM<T>();
		if(hasBegun) throw new AutomatonException("Have already begun constructing FSM.");
		hasBegun = true;
	}

	@Override
	public void endFSM() throws AutomatonException {
		if (initialState == null)
			throw new AutomatonException("Couldn't create FSM, initial state wasn't set.");
		StudentFSM<T> newFSM = new StudentFSM<T>();
		newFSM.setInitialState(initialState);
		newFSM.setLog(new StringConsoleLogger("TestLogger"));
		newFSM.reset();
		newFSM.setStates(this.states);
		fsm = newFSM;
		
	}

	@Override
	public FSM<T> getFSM() throws AutomatonException {
		if(fsm==null) throw new AutomatonException("FSM is not finished yet.");
		return fsm;
	}

	@Override
	public void state(String name, boolean isInitialState) throws AutomatonException {
		states.put(name, new State<T>(name));
		if(isInitialState) {
			if(initialState!=null)
				throw new AutomatonException("Only one initial state allowed.");
			initialState = states.get(name);
		}

	}

	@Override
	public void transition(String startState, String targetState, String transitionName,
			LogicExpression<T> exp) throws AutomatonException {
		if (!this.states.containsKey(startState))
			throw new AutomatonException("Missing start state " + startState + ", for transition " + transitionName);
		if (!this.states.containsKey(targetState))
			throw new AutomatonException("Missing target state " + targetState + ", for transition " + transitionName);
				
		State<T> changeThis = states.get(startState);
		StateTransition<T> newTransition = new StateTransition<T>();
		newTransition.setStartState(states.get(startState));
		newTransition.setTargetState(states.get(targetState));
		newTransition.setName(transitionName);
		newTransition.setExp(exp);
		changeThis.addTransition(newTransition);
	}

}
