package mis.gdi1lab07.student;

import java.io.Writer;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSMHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * This class must be implemented by students.
 */
public class StudentHfsmXmlPrinter<ENV> implements HFSMHandler<ENV> {

	/**
	 * This constructor MUST be implemented in order to run
	 * automated tests. StudentHfsmXmlPrinter MUST be fully functional after
	 * instantiation with this constructor!
	 * 
	 * @param w Writer to be used for output.
	 */
	public StudentHfsmXmlPrinter(Writer w) {
		//TODO This constructor MUST be implemented correctly!!
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
			LogicExpression<ENV> exp) throws AutomatonException {
		// TODO Auto-generated method stub

	}

}
