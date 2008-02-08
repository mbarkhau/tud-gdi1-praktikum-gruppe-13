package mis.gdi1lab07.automaton.logic;

/**
 * Interface for storing and retrieving boolean values that are bound to a name.
 * 
 * @author Oliver Schwahn
 */
public interface BooleanVariables {

	/**
	 * Adds a new boolean value and binds it to the given name.
	 * 
	 * @param name Name to be bound to given value.
	 * @param value Value to be bound.
	 */
	public void add(String name, boolean value);

	/**
	 * Retrieves the value that is bound to the given name.
	 * 
	 * @param name Name whose value should be retrieved.
	 * @return Value that is bound to the given name.
	 * @throws UnknownNameException If given name could not be found.
	 */
	public boolean get(String name) throws UnknownNameException;
}
