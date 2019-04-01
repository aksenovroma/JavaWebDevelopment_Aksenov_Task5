package by.epam.javatraining.aksenov.task5.model.exception;

public class TechnicalProjectException extends BusProjectException {
    public TechnicalProjectException() {
    }

    public TechnicalProjectException(String message) {
        super(message);
    }

    public TechnicalProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalProjectException(Throwable cause) {
        super(cause);
    }
}
