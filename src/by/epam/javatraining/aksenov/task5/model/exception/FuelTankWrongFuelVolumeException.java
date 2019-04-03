package by.epam.javatraining.aksenov.task5.model.exception;

public class FuelTankWrongFuelVolumeException extends WrongArgumentException {
    public FuelTankWrongFuelVolumeException() {
    }

    public FuelTankWrongFuelVolumeException(String message) {
        super(message);
    }
}
