package be.vdab.util;

public class DatumException extends Exception {

    /**
     * Default Constructor DatumException
     */
    public DatumException() {
        super();
    }

    /**
     * Constructor DatumException
     * @param message
     */
    public DatumException(String message) {
        super(message);
    }

    /**
     * Constructor DatumException
     * @param message
     * @param cause
     */
    public DatumException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor DatumException
     * @param cause
     */
    public DatumException(Throwable cause) {
        super(cause);
    }
}
