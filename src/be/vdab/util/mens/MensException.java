package be.vdab.util.mens;

public class MensException extends RuntimeException {
    /**
     * Default Constructor
     */
    public MensException() {
    }

    /**
     * Constructor MensException
     * @param message
     */
    public MensException(String message) {
        super(message);
    }

    /**
     * Constructor MensException
     * @param message
     * @param cause
     */
    public MensException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor MensException
     * @param cause
     */
    public MensException(Throwable cause) {
        super(cause);
    }
}
