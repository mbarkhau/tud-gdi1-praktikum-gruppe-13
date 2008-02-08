package mis.gdi1lab07.automaton;

import mis.gdi1lab07.automaton.logic.LogExpException;

/**
 * Interface for building HFSMs. By implementing this Interface HFSMs can be built during
 * runtime e.g. from a serialized representation.
 * 
 * @param <ENV> Type of built HFSM's input context.
 */
public interface HFSMBuilder<ENV> extends HFSMHandler<ENV> {

	/**
	 * Gets the HFSM that has been built by this HFSMBuilder in response to information that
	 * has been received via the {@link HFSMHandler} interface.
	 * 
	 * @return The built HFSM.
	 * @throws LogExpException
	 */
	public HFSM<ENV> getHFSM() throws AutomatonException;

}
