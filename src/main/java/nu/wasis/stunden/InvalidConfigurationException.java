package nu.wasis.stunden;

public class InvalidConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidConfigurationException() {
    }

    public InvalidConfigurationException(final String message) {
        super(message);
    }

    public InvalidConfigurationException(final Throwable cause) {
        super(cause);
    }

    public InvalidConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidConfigurationException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
