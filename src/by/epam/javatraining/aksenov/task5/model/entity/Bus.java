package by.epam.javatraining.aksenov.task5.model.entity;

import by.epam.javatraining.aksenov.task5.model.exception.BusNumberWrongArgumentException;
import by.epam.javatraining.aksenov.task5.model.exception.BusPassengerWrongArgumentException;

public class Bus {
    private String number;
    private int passenger;

    public Bus(String number, int passenger) {
        this.number = number;
        this.passenger = passenger;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) throws BusNumberWrongArgumentException{
        if (number == null) {
            throw new BusNumberWrongArgumentException();
        }
        this.number = number;
    }

    public int getPassenger() {
        return passenger;
    }

    public boolean checkPassenger() {
        if (passenger >= 10) {
            return false;
        }
        return true;
    }

    public void addPassenger(int passenger) {
        if (passenger >= 0) {
            this.passenger += passenger;
        }
    }

    public void setPassenger(int passenger) throws BusPassengerWrongArgumentException{
        if (passenger < 0) {
            throw new BusPassengerWrongArgumentException();
        }
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number='" + number + '\'' +
                ", passenger=" + passenger +
                '}';
    }
}
