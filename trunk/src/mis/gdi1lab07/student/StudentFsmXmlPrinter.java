package mis.gdi1lab07.student;

import java.io.IOException;
import java.io.Writer;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.FSMHandler;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * This class must be implemented by students.
 */
public class StudentFsmXmlPrinter<ENV> implements FSMHandler<ENV> {

	private static String XML_HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
	
	private static String FSM_TAG_START = "<fsm name=\"StudentFSM\">\n";
	
	private static String FSM_TAG_END = "</fsm>\n";
	
	private static String STATE_TAG_END = " </state>\n";
	
	private final Writer w;
	
	private boolean stateOpen = false;
	
	private final StringBuffer buffer;
	
	/**
	 * This constructor MUST be implemented in order to run
	 * automated tests. StudentFsmXmlPrinter MUST be fully functional after
	 * instantiation with this constructor!
	 * 
	 * @param w Writer to be used for output.
	 */
	public StudentFsmXmlPrinter(Writer w) {
		this.w = w;
		buffer = new StringBuffer();
	}

	@Override
	public void beginFSM() throws AutomatonException {
		buffer.append(XML_HEAD);
		buffer.append(FSM_TAG_START);
	}

	@Override
	public void endFSM() throws AutomatonException {
		if (stateOpen)
			buffer.append(STATE_TAG_END);
			
		buffer.append(FSM_TAG_END);
		try {
			w.write(buffer.toString());
		} catch (IOException e) {
			throw new AutomatonException(e);
		}
	}

	@Override
	public void state(String name, boolean isInitialState) throws AutomatonException {
		if (stateOpen)
			buffer.append(STATE_TAG_END);
		stateOpen = true;
		buffer.append(" <state name=\"" + name + "\"");
		
		if (isInitialState)
			buffer.append(" initial=\"true\"");
		
		buffer.append(">\n");
	}

	@Override
	public void transition(String startState, String targetState, String transitionName,
			LogicExpression<ENV> exp) throws AutomatonException {
		if (!stateOpen)
			throw new AutomatonException("Serialzation failed: transition must be within a state.");
		StudentLogExpPrettyPrinter<ENV> printer = new StudentLogExpPrettyPrinter<ENV>();
		try {
			exp.serialize(printer);
		} catch (LogExpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer.append("  <transition target=\"" + targetState + "\">" + printer.toString() + "</transition>\n");		
	}

}
