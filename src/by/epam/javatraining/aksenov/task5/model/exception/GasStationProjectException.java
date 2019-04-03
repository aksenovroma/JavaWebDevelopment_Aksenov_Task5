package by.epam.javatraining.aksenov.task5.model.exception;

public class GasStationProjectException extends Exception {
    public GasStationProjectException() {
    }

    public GasStationProjectException(String message) {
        super(message);
    }

    public GasStationProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public GasStationProjectException(Throwable cause) {
        super(cause);
    }
}
