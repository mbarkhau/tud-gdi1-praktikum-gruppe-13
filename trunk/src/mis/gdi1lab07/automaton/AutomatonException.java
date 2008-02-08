package mis.gdi1lab07.automaton;

@SuppressWarnings("serial")
public class AutomatonException extends Exception {

	/**
	 * Constructor.
	 */
	public AutomatonException() {
		super();
	}

	/**
	 * Constructor specifying an error message.
	 * 
	 * @param message Error massage.
	 */
	public AutomatonException(String message) {
		super(message);
	}

	/**
	 * Constructor specifying a {@link Throwable} as the cause of this exception.
	 * 
	 * @param cause Exception cause.
	 */
	public AutomatonException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor specifying an error message and a {@link Throwable} as the cause of this
	 * exception.
	 * 
	 * @param message Error massage.
	 * @param cause Exception cause.
	 */
	public AutomatonException(String message, Throwable cause) {
		super(message, cause);
	}
}
