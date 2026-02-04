package bmo;

/**
 * Represents exceptions specific to the Bmo application.
 */
public class BmoException extends Exception {
    /**
     * Constructs a new BmoException with the specified detail message.
     *
     * @param message The detail message.
     */
    public BmoException(String message) {
        super(message);
    }
}
