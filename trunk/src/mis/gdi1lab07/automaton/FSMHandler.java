package mis.gdi1lab07.automaton;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * Interface for handling FSMs. By implementing this interface FSMs can e.g. be serialized
 * or analyzed.<br />
 * <br>
 * This interface should be used corresponding to the following protocol:<br />
 * <ol>
 * <li>Inform handler with {@link FSMHandler#beginHandling()} that FSM processing is
 * about to start.</li>
 * <li>Inform handler with {@link FSMHandler#state(String, boolean)} about a (currently
 * unhandled) FSM state.</li>
 * <li>Inform handler with
 * {@link FSMHandler#transition(String, String, String,LogicExpression)} about all FSM
 * transitions that start at the state handled in (2).</li>
 * <li>If FSM has unhandled states, goto (2).</li>
 * <li>Inform handler with {@link FSMHandler#endHandling()} that FSM processing is
 * finished.</li>
 * </ol>
 * 
 * @param <ENV> Type of FSM's input context.
 */
public interface FSMHandler<ENV> {
	
	/**
	 * Informs this FSMHandler about the beginning of an FSM description.
	 **/
	public void beginFSM() throws AutomatonException;

	/**
	 * Informs this FSMHandler about the end of an FSM description.
	 **/
	public void endFSM() throws AutomatonException;

	/**
	 * Informs this FSMHandler about a state of the specified name, and whether that state
	 * is an initial one.
	 * 
	 * @param name State's name.
	 * @param isInitialState True, if state is an initial one.
	 * @throws LogExpException
	 */
	public void state(String name, boolean isInitialState) throws AutomatonException;

	/**
	 * Informs this FSMHandler about a transition from one state to another, and about the
	 * logic expression that is associated to that transition.
	 * 
	 * @param startState Transition's starting state.
	 * @param targetState Transition's target state.
	 * @param transitionName Transition's name.
	 * @param exp Transition's logic expression
	 * @throws LogExpException
	 */
	public void transition(String startState, String targetState, String transitionName,
			LogicExpression<ENV> exp) throws AutomatonException;
}
