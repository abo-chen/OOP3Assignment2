package exceptions;

/**
 * Exception thrown when attempting to access an element from an empty queue.
 */
public class EmptyQueueException extends RuntimeException {

    /**
     * Constructs an EmptyQueueException with no detail message.
     */
    public EmptyQueueException() {
        super();
    }

    /**
     * Constructs an EmptyQueueException with the specified detail message.
     *
     * @param message the detail message.
     */
    public EmptyQueueException(String message) {
        super(message);
    }

    /**
     * Constructs an EmptyQueueException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of this exception
     */
    public EmptyQueueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an EmptyQueueException with the specified cause.
     *
     * @param cause the cause of this exception
     */
    public EmptyQueueException(Throwable cause) {
        super(cause);
    }
}
