package mis.gdi1lab07.student;

import java.io.Writer;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSMHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * This class must be implemented by students.
 */
public class StudentHfsmXmlPrinter<ENV> implements HFSMHandler<ENV> {

	
	private final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	
	private final String HFSM_TAG_START = "<hfsm name=\"";
	
	private static String HFSM_TAG_END = "</hfsm>\n";
	
	private static String STATE_TAG_END = " </state>\n";
	
	private static String subs = "<substates>\n";
	
	private final Writer w;
	
	private boolean stateOpen = false;
	
	private final StringBuffer buffer;
	
	
	
	/**
	 * This constructor MUST be implemented in order to run
	 * automated tests. StudentHfsmXmlPrinter MUST be fully functional after
	 * instantiation with this constructor!
	 * 
	 * @param w Writer to be used for output.
	 */
	public StudentHfsmXmlPrinter(Writer w) {
		this.w = w;
		buffer = new StringBuffer();
	}
	
	@Override
	public void beginState(String name, boolean isInitialState) throws AutomatonException {
		buffer.append(this.XML_HEADER);
		buffer.append(this.HFSM_TAG_START);

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
	
	
	private String stateTag(String name, boolean initial){
		return "<state name=\""+name+" initial=\""+initial+"\">\n";
	}
	
	private String transTag(String target, String transName){
		return ("<transition target=\""+target+"\">"+transName+"\"</transition>\n");
	}
	

}
