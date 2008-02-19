package mis.gdi1lab07.student;

import java.io.IOException;
import java.io.Writer;
import java.util.Stack;

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
	
	private static String STATE_TAG_END = "</state>\n";
	
	private static String SUBSTATES  = "<substates>\n";
	
	private static String SUBSTATE_TAG_END = "</substates>\n";
	
	private final Writer w;
	
	private final boolean STATE = true;
	
	private final boolean SUBSTATE = false;
	
	private Stack<Boolean> tagStack = new Stack<Boolean>();
	
	private final StringBuffer buffer;
	
	private int emptyCharCounter = 0;
	
	
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
		buffer.append(this.XML_HEADER);
		buffer.append(this.HFSM_TAG_START);
	}
	
	@Override
	public void beginState(String name, boolean isInitialState) throws AutomatonException {
		this.emptyCharCounter++;
		buffer.append(emptyChars()+this.stateTag(name, isInitialState));
		this.emptyCharCounter++;
		buffer.append(emptyChars()+this.SUBSTATES);
		this.tagStack.push(SUBSTATE);
		

	}

	@Override
	public void endState() throws AutomatonException {
		this.emptyCharCounter--;
		
		if(this.tagStack.peek()==STATE)
			buffer.append(this.emptyChars()+this.STATE_TAG_END);
		else{
			
			buffer.append(this.emptyChars()+this.SUBSTATE_TAG_END);
			
		}
		
		this.tagStack.pop();
		
		
		if(tagStack.size()==0){
			buffer.append(this.HFSM_TAG_END);
		try {
			w.write(buffer.toString());
		} catch (IOException e) {
			throw new AutomatonException(e);
		}}

	}

	@Override
	public void state(String name, boolean isInitialState) throws AutomatonException {
		this.emptyCharCounter++;
		buffer.append(emptyChars() + this.stateTag(name, isInitialState));
		this.tagStack.push(STATE);
	}

	@Override
	public void transition(String startState, String targetState, String transitionName,
			LogicExpression<ENV> exp) throws AutomatonException {
		this.emptyCharCounter++;
		buffer.append(this.emptyChars()+this.transTag(targetState, transitionName));
		this.emptyCharCounter--;
	}
	
	
	private String stateTag(String name, boolean initial){
		return (emptyChars()
				+ "<state name=\""+name+" initial=\""+initial+"\">\n");
	}
	
	private String transTag(String target, String transName){
		return (emptyChars()+"<transition target=\""+target+"\">"+transName+"\"</transition>\n");
	}
	
	private String emptyChars(){
		String temp="";
		for(int i=0; i<this.emptyCharCounter;i++){
			temp += " ";
		}
		return temp;
		
	}
}
