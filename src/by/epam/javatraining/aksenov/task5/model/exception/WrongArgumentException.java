package by.epam.javatraining.aksenov.task5.model.exception;

public class WrongArgumentException extends LogicProjectException {
    public WrongArgumentException() {
    }

    public WrongArgumentException(String message) {
        super(message);
    }
}
