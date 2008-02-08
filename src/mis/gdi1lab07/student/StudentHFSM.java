package mis.gdi1lab07.student;

import utilities.Logger;
import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMHandler;

/**
 * This class must be implemented by students.
 */
public class StudentHFSM<T> implements HFSM<T> {

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentHFSM MUST be fully functional after
	 * instantiation with this constructor!
	 */
	public StudentHFSM() {
		//TODO This constructor MUST be implemented correctly!!
	}
	
	@Override
	public void doOutput() throws AutomatonException {
		// TODO Auto-generated method stub

	}

	@Override
	public HFSM<T> input(Object context) throws AutomatonException {
		// TODO Auto-generated method stub
		return null;
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
