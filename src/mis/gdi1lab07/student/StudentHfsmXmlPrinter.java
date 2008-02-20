package mis.gdi1lab07.student;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMHandler;
import mis.gdi1lab07.automaton.HFSMTransition;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * This class must be implemented by students.
 */
public class StudentHfsmXmlPrinter<ENV> implements HFSMHandler<ENV> {

	private final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";

	private final String HFSM_TAG_START = "<hfsm name=\"StudentHFSM\">\n";

	private static String HFSM_TAG_END = "</state>\n</hfsm>\n";

	private static String STATE_TAG_END = "</state>\n";

	private static String SUBSTATE_TAG = "<substates>\n";

	private static String SUBSTATE_TAG_END = "</substates>\n";

	private final Writer w;
	
	private Stack<String> tagStack = new Stack<String>();
	
	private int openStateCount = 0;

	// private Map<HFSM<ENV>, List<HFSMTransition<ENV>>> transitions = new
	// HashMap<HFSM<ENV>, List<HFSMTransition<ENV>>>(); o_O
	
	private final StringBuffer buffer;

	/**
	 * This constructor MUST be implemented in order to run automated tests.
	 * StudentHfsmXmlPrinter MUST be fully functional after instantiation with
	 * this constructor!
	 * 
	 * @param w
	 *            Writer to be used for output.
	 */
	public StudentHfsmXmlPrinter(Writer w) {
		this.w = w;
		buffer = new StringBuffer();
		buffer.append(this.XML_HEADER);
		buffer.append(this.HFSM_TAG_START);
	}

	@Override
	public void beginState(String name, boolean isInitialState)
			throws AutomatonException {

		openStateCount++;
		if(tagStack.size()>0) {
			if(tagStack.peek().equals(STATE_TAG_END)) {
				buffer.append(emptyChars(tagStack.size()) + STATE_TAG_END);
				tagStack.pop();
			}
			
		}
		buffer.append(stateTag(name, isInitialState));
		tagStack.push(STATE_TAG_END);
		tagStack.push(SUBSTATE_TAG_END);
		buffer.append(emptyChars(tagStack.size()) + SUBSTATE_TAG);
	}

	@Override
	public void endState() throws AutomatonException {
		if(STATE_TAG_END.equals(tagStack.peek())) {
			buffer.append(emptyChars(tagStack.size() - 1) + tagStack.pop());
		}
		if(SUBSTATE_TAG_END.equals(tagStack.peek())){
			buffer.append(emptyChars(tagStack.size()- 1) + tagStack.pop());
		}
		
		openStateCount--;
		
		if(openStateCount==0) {
			buffer.append(HFSM_TAG_END);
			try {
				w.write(buffer.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new AutomatonException(e);
			}
		}
	}

	@Override
	public void state(String name, boolean isInitialState)
			throws AutomatonException {
		
		System.out.println("Name: " + name + "Stackpeek: " + tagStack.peek());
		if(tagStack.peek().equals(STATE_TAG_END)){
			buffer.append(emptyChars(tagStack.size()) + STATE_TAG_END);
			tagStack.pop();
		}
		else if(tagStack.peek().equals(SUBSTATE_TAG_END)) {
			//buffer.append(emptyChars(tagStack.size()) + STATE_TAG_END);
			//tagStack.push(STATE_TAG_END);
		}
		tagStack.push(STATE_TAG_END);
		buffer.append(stateTag(name, isInitialState));
	}

	@Override
	public void transition(String startState, String targetState,
			String transitionName, LogicExpression<ENV> exp)
			throws AutomatonException {
		buffer.append(transTag(targetState, transitionName));
	}

	private String stateTag(String name, boolean initial) {
		return (emptyChars(tagStack.size()) + "<state name=\"" + name
				+ "\" initial=\"" + initial + "\">\n");
	}

	private String transTag(String target, String transName) {
		return (emptyChars(tagStack.size()) + "<transition target=\"" + target
				+ "\">" + transName + "</transition>\n");
	}

	private String emptyChars(int count) {
		String temp = "";
		for (int i = 0; i < count; i++) {
			temp += " ";
		}
		return temp;

	}

}
/*
class Transition<ENV> {
	String startState;
	String targetState;
	String transitionName;
	LogicExpression<ENV> exp;

	public Transition(String startState, String targetState,
			String transitionName, LogicExpression<ENV> exp) {
		super();
		this.startState = startState;
		this.targetState = targetState;
		this.transitionName = transitionName;
		this.exp = exp;
	}
}
*/