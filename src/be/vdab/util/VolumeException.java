package be.vdab.util;

public class VolumeException extends Exception {
    /**
     * Default Constructor VolumeException
     */
    public VolumeException() {   }

    /**
     * Constructor VolumeException
     * @param message
     */
    public VolumeException(String message) {
        super(message);
    }

    /**
     * Constructor VolumeException
     * @param message
     * @param cause
     */
    public VolumeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor VolumeException
     * @param cause
     */
    public VolumeException(Throwable cause) {
        super(cause);
    }
}
