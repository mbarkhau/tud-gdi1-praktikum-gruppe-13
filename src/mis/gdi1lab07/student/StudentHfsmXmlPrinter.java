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

	private static String HFSM_TAG_END = "</hfsm>\n";

	private static String STATE_TAG_END = "</state>\n";

	private static String SUBSTATE_TAG = "<substates>\n";

	private static String SUBSTATE_TAG_END = "</substates>\n";

	private final Writer w;

	private Stack<String> tagStack = new Stack<String>();

	private Stack<String> stateStack = new Stack<String>();

	// private Map<HFSM<ENV>, List<HFSMTransition<ENV>>> transitions = new
	// HashMap<HFSM<ENV>, List<HFSMTransition<ENV>>>(); o_O
	private Map<String, List<Transition<ENV>>> stateToTransitions = new HashMap<String, List<Transition<ENV>>>();

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
		buffer.append(stateTag(name, isInitialState));
		tagStack.push(STATE_TAG_END);
		stateStack.push(name);
		buffer.append(emptyChars(tagStack.size()) + SUBSTATE_TAG);
		tagStack.push(SUBSTATE_TAG_END);
	}

	@Override
	public void endState() throws AutomatonException {

		if (STATE_TAG_END.equals(tagStack.peek())){
			// möglicherweise noch nicht geschlossener state
			String state = stateStack.pop();
			for (Transition<ENV> trans : stateToTransitions.get(state)) 
				buffer.append(transTag(trans.targetState, trans.transitionName));

			buffer.append(emptyChars(tagStack.size() - 1) + tagStack.pop());
		}
		if (SUBSTATE_TAG_END.equals(tagStack.peek())) {
			// substates
			buffer.append(emptyChars(tagStack.size() - 1) + tagStack.pop()); 

			String state = stateStack.pop();
			for (Transition<ENV> trans : stateToTransitions.get(state)) 
				buffer.append(transTag(trans.targetState, trans.transitionName));

			// end state
			buffer.append(emptyChars(tagStack.size() - 1) + tagStack.pop()); 
		}

		if (tagStack.size() == 0) {
			buffer.append(HFSM_TAG_END);
			try {
				w.write(buffer.toString());
			} catch (IOException e) {
				throw new AutomatonException(e);
			}
		}
	}

	@Override
	public void state(String name, boolean isInitialState)
			throws AutomatonException {
		if (STATE_TAG_END.equals(tagStack.peek()))
			buffer.append(emptyChars(tagStack.size() - 1) + tagStack.pop());

		buffer.append(stateTag(name, isInitialState));
		stateStack.push(name);
		this.tagStack.push(STATE_TAG_END);
	}

	@Override
	public void transition(String startState, String targetState,
			String transitionName, LogicExpression<ENV> exp)
			throws AutomatonException {
		if (SUBSTATE_TAG_END.equals(tagStack.peek()))
			buffer.append(emptyChars(tagStack.size() - 1) + tagStack.pop());
		if (!stateToTransitions.containsKey(startState))
			stateToTransitions.put(startState, new ArrayList<Transition<ENV>>());

		stateToTransitions.get(startState).add(
				new Transition<ENV>(startState, targetState, transitionName,
						exp));
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
