package mis.gdi1lab07.student;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import mis.gdi1lab07.automaton.logic.BooleanVariables;
import mis.gdi1lab07.automaton.logic.UnknownNameException;

/**
 * This class must be implemented by students.
 */
public class StudentBooleanVariables implements BooleanVariables {

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentBooleanValues MUST be fully functional after
	 * instantiation with this constructor!
	 */

	private Hashtable values;

	public StudentBooleanVariables() {
		// TODO This constructor MUST be implemented correctly!!
		values = new Hashtable();
	}

	@Override
	public void add(String name, boolean value) {
		// TODO Auto-generated method stub
		values.put(name, value);

	}

	@Override
	public boolean get(String name) throws UnknownNameException {
		// TODO Auto-generated method stub
		if (values.containsKey(name)) {
			Boolean returnValue = (Boolean) values.get(name);
			return returnValue;

		} else
			throw new UnknownNameException("Name " + name + " is unknown!");
	}

}
