package by.epam.javatraining.aksenov.task5.model.exception;

public class BusNumberWrongArgumentException extends LogicProjectException {
    public BusNumberWrongArgumentException() {
    }

    public BusNumberWrongArgumentException(String message) {
        super(message);
    }
}
