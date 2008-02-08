package mis.gdi1lab07.automaton;

import utilities.Logger;

/**
 * This interface represents basic functionality that must be implemented by all
 * (non-hierarchical) finite state machines.
 * 
 * @param <ENV> Type of FSM's input context.
 */
public interface FSM<ENV> {

	/**
	 * Resets the current state of this FSM to its initial state.
	 * 
	 * @throws AutomatonException
	 */
	public void reset() throws AutomatonException;

	/**
	 * Performs a state change as follows:<br />
	 * If a transition has been registered under the specified input event, the current
	 * state of this FSM is set to the successor state defined by the transition. If no such
	 * transition is known, the current state is not changed. Additionally, logs the new
	 * current resp. unchanged state.
	 * 
	 * @param input Automaton input to be used.
	 * @throws AutomatonException
	 */
	public void input(ENV input) throws AutomatonException;

	/**
	 * Produces a serialized representation of this FSM by calling the specified handler's
	 * callback-methods.<br />
	 * <br />
	 * Serialization should be done corresponding to the following draft:<br />
	 * <ol>
	 * <li>Serialize (currently unserialized) FSM state</li>
	 * <li>Serialize all FSM transitions starting at state serialized in (1)</li>
	 * <li>If FSM has unserialized states, goto (1)</li>
	 * </ol>
	 * 
	 * @param handler Handler to be used for serialization.
	 * @throws AutomatonException
	 */
	public void serialize(FSMHandler<ENV> handler) throws AutomatonException;

	/**
	 * Sets the log of this FSM.
	 * 
	 * @param log Log to be used for logging.
	 * @throws AutomatonException
	 */
	public void setLog(Logger log) throws AutomatonException;
}
