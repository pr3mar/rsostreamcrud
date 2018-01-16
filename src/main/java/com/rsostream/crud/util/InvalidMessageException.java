package com.rsostream.crud.util;

public class InvalidMessageException extends Exception {
    private static final long serialVersionUID = 6765369561906450023L;

    public InvalidMessageException() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public InvalidMessageException(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public InvalidMessageException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public InvalidMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
