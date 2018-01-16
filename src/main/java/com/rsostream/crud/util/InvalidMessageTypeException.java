package com.rsostream.crud.util;

public class InvalidMessageTypeException extends InvalidMessageException {
    private static final long serialVersionUID = 6765369561906450023L;

    public InvalidMessageTypeException() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public InvalidMessageTypeException(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public InvalidMessageTypeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public InvalidMessageTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
