package nu.wasis.stunden.exception;

import nu.wasis.stunden.model.WorkPeriod;

/**
 * Thrown if two {@link WorkPeriod}s to be combined contain duplicate entries.
 */
public class NonUniqueDayException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NonUniqueDayException() {
    }

    public NonUniqueDayException(final String message) {
        super(message);
    }

    public NonUniqueDayException(final Throwable cause) {
        super(cause);
    }

    public NonUniqueDayException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NonUniqueDayException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
