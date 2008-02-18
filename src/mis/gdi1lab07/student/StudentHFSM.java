package mis.gdi1lab07.student;

import java.util.ArrayList;
import java.util.List;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.FSM;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMHandler;
import mis.gdi1lab07.automaton.HFSMTransition;
import mis.gdi1lab07.automaton.logic.LogExpException;
import utilities.Logger;

/**
 * This class must be implemented by students.
 */
public class StudentHFSM<T> implements HFSM<T> {

	private HFSM<T> stateHFSM;

	private HFSM<T> initialState;

	private List<HFSM<T>> states = new ArrayList<HFSM<T>>();

	private List<HFSMTransition<T>> transitions = new ArrayList<HFSMTransition<T>>();

	private FSM<T> stateFSM;

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
		// TODO Auto-generated method stub

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
		if (nextState != null){
			stateHFSM = nextState;
			return nextState;
		}
		doOutput();
		return this;
	}

	@Override
	public void output() throws AutomatonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() throws AutomatonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void serialize(HFSMHandler<T> handler) throws AutomatonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLog(Logger log) {
		// TODO Auto-generated method stub

	}

}
