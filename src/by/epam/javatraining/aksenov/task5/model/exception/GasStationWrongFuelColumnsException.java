package by.epam.javatraining.aksenov.task5.model.exception;

public class GasStationWrongFuelColumnsException extends WrongArgumentException {
    public GasStationWrongFuelColumnsException() {
    }

    public GasStationWrongFuelColumnsException(String message) {
        super(message);
    }
}
