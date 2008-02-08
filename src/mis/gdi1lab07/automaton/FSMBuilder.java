package mis.gdi1lab07.automaton;

import mis.gdi1lab07.automaton.logic.LogExpException;

/**
 * Interface for building FSMs. By implementing this Interface FSMs can be built during
 * runtime e.g. from a serialized representation.
 * 
 * @param <ENV> Type of FSM's input context.
 */
public interface FSMBuilder<ENV> extends FSMHandler<ENV> {

	/**
	 * Gets the FSM that has been built by this FSMBuilder in response to information that
	 * has been received via the {@link FSMHandler} interface.
	 * 
	 * @return The built FSM.
	 * @throws LogExpException
	 */
	public FSM<ENV> getFSM() throws AutomatonException;
}
