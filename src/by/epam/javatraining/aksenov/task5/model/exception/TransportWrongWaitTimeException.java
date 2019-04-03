package by.epam.javatraining.aksenov.task5.model.exception;

public class TransportWrongWaitTimeException extends WrongArgumentException {
    public TransportWrongWaitTimeException() {
    }

    public TransportWrongWaitTimeException(String message) {
        super(message);
    }
}
