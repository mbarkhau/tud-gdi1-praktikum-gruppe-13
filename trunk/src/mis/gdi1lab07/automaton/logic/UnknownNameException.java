package mis.gdi1lab07.automaton.logic;

@SuppressWarnings("serial")
public class UnknownNameException extends RuntimeException {
	/**
	 * Constructor.
	 */
	public UnknownNameException() {
		super();
	}

	/**
	 * Constructor specifying an error message.
	 * 
	 * @param message Error massage.
	 */
	public UnknownNameException(String message) {
		super(message);
	}

	/**
	 * Constructor specifying a {@link Throwable} as the cause of this exception.
	 * 
	 * @param cause Exception cause.
	 */
	public UnknownNameException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor specifying an error message and a {@link Throwable} as the cause of this
	 * exception.
	 * 
	 * @param message Error massage.
	 * @param cause Exception cause.
	 */
	public UnknownNameException(String message, Throwable cause) {
		super(message, cause);
	}
}
