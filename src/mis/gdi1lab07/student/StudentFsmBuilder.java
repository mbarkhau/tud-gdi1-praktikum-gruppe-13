package mis.gdi1lab07.student;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.FSM;
import mis.gdi1lab07.automaton.FSMBuilder;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * This class must be implemented by students.
 */
public class StudentFsmBuilder<T> implements FSMBuilder<T> {
	
	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentFsmBuilder MUST be fully functional after
	 * instantiation with this constructor!
	 */
	public StudentFsmBuilder() {
		//TODO This constructor MUST be implemented correctly!!
	}

	@Override
	public void beginFSM() throws AutomatonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endFSM() throws AutomatonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FSM<T> getFSM() throws AutomatonException {
		// TODO Auto-generated method stub
		return null;
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
