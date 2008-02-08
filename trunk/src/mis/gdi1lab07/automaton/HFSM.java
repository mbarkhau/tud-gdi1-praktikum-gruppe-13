package mis.gdi1lab07.automaton;

import utilities.Logger;

/**
 * This interface represents basic functionality that must be implemented by all
 * hierarchical finite state machines.
 * 
 * @param <ENV> Type of HFSM's input context.
 */
public interface HFSM<ENV> {

	/**
	 * Resets the current state of this HFSM to its initial state. Also resets all contained
	 * (sub) HFSMs recursively.
	 * 
	 * @throws AutomatonException
	 */
	public void reset() throws AutomatonException;

	/**
	 * Performs a state change according to the specified input context.<br />
	 * If this HFSM has exactly one matching transition to a successor state, this HFSM is
	 * reset (recursively), and the successor state's output-method is called.<br />
	 * If this HFSM does not have any matching transitions, this HFSM's doOutput-method is
	 * called, and the specified input context is passed on to the next level recursively
	 * (by calling the input-method of this HFSM's current state).
	 * 
	 * @param context Context to be used as input.
	 * @return If a matching transition exists, the corresponding successor state. This
	 *         HFSM, otherwise.
	 * @throws AutomatonException In case multiple transitions match the specified input
	 *           (input conflict).
	 */
	public HFSM<ENV> input(ENV context) throws AutomatonException;

	/**
	 * Performs output for this HFSM recursively. First, calls the doOutput-method of this
	 * HFSM, and then calls the output-method of this HFSM's current state.
	 * 
	 * @throws AutomatonException
	 */
	public void output() throws AutomatonException;

	/**
	 * Defines the actual output of this HFSM according to the "Template Method" design
	 * pattern, and logs this HFSM. Called by the output-method.
	 * 
	 * @throws AutomatonException
	 */
	public void doOutput() throws AutomatonException;

	/**
	 * Produces a serialized representation of this HFSM by calling the specified handler's
	 * callback-methods.<br />
	 * <br />
	 * Serialization should be done corresponding to the following draft:<br />
	 * <ol>
	 * <li>Serialize this HFSM as state (recursively including substates)</li>
	 * <li>Serialize all HFSM transitions starting at state serialized in (1)</li>
	 * <li>If HFSM has unserialized states, goto (1)</li>
	 * </ol>
	 * 
	 * @param handler Handler to be used for serialization.
	 * @throws AutomatonException
	 */
	public void serialize(HFSMHandler<ENV> handler) throws AutomatonException;

	/**
	 * Sets the log of this HFSM.
	 * 
	 * @param log Log to be used for logging.
	 */
	public void setLog(Logger log);
}
