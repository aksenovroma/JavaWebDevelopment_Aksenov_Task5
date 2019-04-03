package by.epam.javatraining.aksenov.task5.controller;

import by.epam.javatraining.aksenov.task5.util.TransportGenerator;

import static properties.ProjectProperties.TRANSPORT_GENERATED_COUNT;

public class Main {
    public static void main(String[] args) {
        TransportGenerator.generate(TRANSPORT_GENERATED_COUNT);
    }
}

