package mis.gdi1lab07.automaton;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * Interface for handling HFSMs. By implementing this interface HFSMs can e.g. be
 * serialized or analyzed.<br />
 * <br>
 * This interface should be used corresponding to the following protocol:<br />
 * <ol>
 * <li>Inform handler with {@link HFSMHandler#beginHandling()} that HFSM processing is
 * about to start.</li>
 * <li>Inform handler about a (currently unhandled) HFSM as state.</li>
 * <li>Inform handler with
 * {@link HFSMHandler#transition(String, String, String, LogicExpression)} about all
 * transitions starting at state handled in (2).</li>
 * <li>If HFSM has unhandled states, goto (2).</li>
 * <li>Inform handler with {@link HFSMHandler#endHandling()} that HFSM processing is
 * finished.</li>
 * </ol>
 * All these steps must be done recursively in order to handle the complete HFSM
 * hierarchy.
 * 
 * @param <ENV> Type of handled HFSM's input context.
 */
public interface HFSMHandler<ENV> {
	
	/**
	 * Informs this HFSMHandler about the beginning of a state definition with specified
	 * name, and whether that state is an initial one.
	 * 
	 * @param name State's name.
	 * @param isInitialState True, if state is an initial one.
	 * @throws LogExpException
	 */
	public void beginState(String name, boolean isInitialState) throws AutomatonException;

	/**
	 * Informs this HFSMHandler about the end of a state definition.
	 * 
	 * @throws LogExpException
	 */
	public void endState() throws AutomatonException;

	/**
	 * Informs this HFSMHandler about a state of the specified name, and whether that state
	 * is an initial one.
	 * 
	 * @param name State's name.
	 * @param isInitialState True, if state is an initial one.
	 * @throws LogExpException
	 */
	public void state(String name, boolean isInitialState) throws AutomatonException;

	/**
	 * Informs this HFSMHandler about a transition from one state to another, and about the
	 * name and the LogicExpression that is associated with that transition.
	 * 
	 * @param startState Transition's starting state.
	 * @param targetState Transition's target state.
	 * @param transitionName Transition's name.
	 * @param exp LogicExpression associated with the transition.
	 * @throws LogExpException
	 */
	public void transition(String startState, String targetState, String transitionName,
			LogicExpression<ENV> exp) throws AutomatonException;
}
