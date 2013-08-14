package nu.wasis.stunden.exception;

public class InvalidArgumentsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidArgumentsException() {
    }

    public InvalidArgumentsException(final String message) {
        super(message);
    }

    public InvalidArgumentsException(final Throwable cause) {
        super(cause);
    }

    public InvalidArgumentsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentsException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
