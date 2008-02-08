package mis.gdi1lab07.automaton.logic;

@SuppressWarnings("serial")
public class LogExpException extends Exception {

	/**
	 * Constructor.
	 */
	public LogExpException() {
		super();
	}

	/**
	 * Constructor specifying an error message.
	 * 
	 * @param message Error massage.
	 */
	public LogExpException(String message) {
		super(message);
	}

	/**
	 * Constructor specifying a {@link Throwable} as the cause of this exception.
	 * 
	 * @param cause Exception cause.
	 */
	public LogExpException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor specifying an error message and a {@link Throwable} as the cause of this
	 * exception.
	 * 
	 * @param message Error massage.
	 * @param cause Exception cause.
	 */
	public LogExpException(String message, Throwable cause) {
		super(message, cause);
	}
}
