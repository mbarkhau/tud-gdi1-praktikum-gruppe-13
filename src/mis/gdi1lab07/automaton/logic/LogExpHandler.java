package mis.gdi1lab07.automaton.logic;


public interface LogExpHandler<ENV> {

	/**
	 * Informs this LogExpBuilder about a starting logic conjunction.
	 * 
	 * @throws LogExpException
	 */
	public void beginAnd() throws LogExpException;

	/**
	 * Informs this LogExpBuilder about an ending logic conjunction.
	 * 
	 * @throws LogExpException
	 */
	public void endAnd() throws LogExpException;

	/**
	 * Informs this LogExpBuilder about a starting logic disjunction.
	 * 
	 * @throws LogExpException
	 */
	public void beginOr() throws LogExpException;

	/**
	 * Informs this LogExpBuilder about an ending logic disjunction.
	 * 
	 * @throws LogExpException
	 */
	public void endOr() throws LogExpException;

	/**
	 * Informs this LogExpBuilder about a starting logic negation.
	 * 
	 * @throws LogExpException
	 */
	public void beginNegation() throws LogExpException;

	/**
	 * Informs this LogExpBuilder about an ending logic negation.
	 * 
	 * @throws LogExpException
	 */
	public void endNegation() throws LogExpException;

	/**
	 * Informs this LogExpBuilder that a constant true expression should be
	 * added.
	 * 
	 * @throws LogExpException
	 */
	public void constTrue() throws LogExpException;

	/**
	 * Informs this LogExpBuilder that a constant false expression should be
	 * added.
	 * 
	 * @throws LogExpException
	 */
	public void constFalse() throws LogExpException;

	/**
	 * Informs the LogExpBuilder that a boolean value should be
	 * evaluated here.
	 * 
	 * @param name
	 *            Name of Value to be evaluated.
	 */
	public void variableReference(String name) throws LogExpException;

}
