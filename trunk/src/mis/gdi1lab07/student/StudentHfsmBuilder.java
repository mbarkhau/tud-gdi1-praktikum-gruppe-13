package mis.gdi1lab07.student;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMBuilder;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * This class must be implemented by students.
 */
public class StudentHfsmBuilder<T> implements HFSMBuilder<T> {

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentHfsmBuilder MUST be fully functional after
	 * instantiation with this constructor!
	 */
	public StudentHfsmBuilder() {
		//TODO This constructor MUST be implemented correctly!!
	}

	@Override
	public HFSM<T> getHFSM() throws AutomatonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beginState(String name, boolean isInitialState) throws AutomatonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endState() throws AutomatonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void state(String name, boolean isInitialState) throws AutomatonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void transition(String startState, String targetState, String transitionName,
			LogicExpression<T> exp) throws AutomatonException {
		// TODO Auto-generated method stub

	}

}
