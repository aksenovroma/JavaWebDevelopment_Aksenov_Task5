package by.epam.javatraining.aksenov.task5.model.exception;

public class BusProjectException extends Exception {
    public BusProjectException() {
    }

    public BusProjectException(String message) {
        super(message);
    }

    public BusProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusProjectException(Throwable cause) {
        super(cause);
    }
}
