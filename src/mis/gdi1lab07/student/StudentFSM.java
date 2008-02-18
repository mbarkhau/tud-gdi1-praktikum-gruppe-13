package mis.gdi1lab07.student;


import java.util.HashMap;
import java.util.Map;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.FSM;
import mis.gdi1lab07.automaton.FSMHandler;
import mis.gdi1lab07.automaton.State;
import mis.gdi1lab07.automaton.logic.LogExpException;
import utilities.LogLevel;
import utilities.Logger;

/**
 * This class must be implemented by students.
 */
public class StudentFSM<T> implements FSM<T> {

	private Logger logger;

	private State<T> initState;
	
	private State<T> currentState;

	private Map<String, State<T>> states = new HashMap<String, State<T>>();
	
	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentFSM MUST be fully functional after instantiation
	 * with this constructor!
	 */
	public StudentFSM() {
	}
	
	public void setInitialState(State<T> initState){
		this.initState = initState;
	}

	@Override
	public void input(T input) throws AutomatonException {
		try {
			this.currentState = currentState.getTransition(input);
		} catch (LogExpException e) {
		}
		logger.log(LogLevel.Info, this.currentState.toString());
	}

	@Override
	public void reset() throws AutomatonException {
		this.currentState = initState;
	}

	@Override
	public void serialize(FSMHandler<T> handler) throws AutomatonException {
		handler.beginFSM();
		for (State<T> state : states.values()) {
			handler.state(state.getName(), state == initState);
			state.serialize(handler);
		}
		handler.endFSM();
	}

	@Override
	public void setLog(Logger log) throws AutomatonException {
		logger = log;
	}

	public void setStates(Map<String, State<T>> states) {
		this.states = states;
	}

}
