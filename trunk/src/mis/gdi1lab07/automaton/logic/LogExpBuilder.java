package mis.gdi1lab07.automaton.logic;


/**
 * Interface for building LogicExpressions. By implementing this interface logic
 * expression can be built during runtime.
 * 
 * @author Christian Thaler, Oliver Schwahn
 * @param <ENV>
 *            Type of built LogicExpression's input context.
 */
public interface LogExpBuilder<ENV> extends LogExpHandler<ENV> {

	/**
	 * Retrieves the LogicExpression that was built by this LogExpBuilder.
	 * 
	 * @return The built LogicExpression.
	 * @throws LogExpException
	 */
	public LogicExpression<ENV> getLogExp() throws LogExpException;
}
