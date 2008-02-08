package mis.gdi1lab07.automaton.logic;

import mis.gdi1lab07.automaton.AutomatonException;

/**
 * This interface provides all functionality needed to evaluate logic
 * expressions. It should not be implemented directly, instead use the abstract
 * base classes for different kinds of logic functions and/or expressions or
 * their concrete implementations. This interface is designed to work together
 * with the FSM and HFSM interfaces to provide advanced transition capabilities.
 * 
 * @author Oliver Schwahn
 * @param <ENV>
 *            Type of LogExpression's eval context.
 */
public interface LogicExpression<ENV> {

	/**
	 * Evaluates the logic expression to a boolean value.
	 * 
	 * @param env
	 *            A context holding all information needed to evaluate the
	 *            expression.
	 * @return True or false according to the evaluation.
	 */
	public boolean eval(ENV env) throws LogExpException;

	/**
	 * Produces a serialized representation of this LogicExpression by calling
	 * the specified handler's callback-methods.
	 * 
	 * @param handler
	 *            Handler to be used for serialization.
	 * @throws AutomatonException
	 */
	public void serialize(LogExpHandler<ENV> handler) throws LogExpException;

}
