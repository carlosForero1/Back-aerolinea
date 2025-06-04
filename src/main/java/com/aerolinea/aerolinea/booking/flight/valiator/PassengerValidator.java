package com.aerolinea.aerolinea.booking.flight.valiator;


import com.aerolinea.aerolinea.booking.flight.entity.Passenger;

import java.util.regex.Pattern;

public class PassengerValidator {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    private static final Pattern DOCUMENT_NUMBER_PATTERN = Pattern.compile("^[a-zA-Z0-9-]+$");


    public static String validatePassenger(Passenger passenger) {
        if (passenger == null) {
            return "El pasajero no puede ser nulo.";
        }


        if (passenger.getName() == null || passenger.getName().trim().isEmpty()) {
            return "El nombre del pasajero no puede estar vacío.";
        }
        if (passenger.getName().length() < 2 || passenger.getName().length() > 100) {
            return "El nombre del pasajero debe tener entre 2 y 100 caracteres.";
        }
        if (!NAME_PATTERN.matcher(passenger.getName()).matches()) {
            return "El nombre del pasajero solo debe contener letras y espacios.";
        }



        if (passenger.getAge() <= 0 || passenger.getAge() > 120) { // Asumimos un rango de edad razonable
            return "La edad del pasajero debe ser un valor entre 1 y 120.";
        }


        if (passenger.getDocumentType() == null || passenger.getDocumentType().trim().isEmpty()) {
            return "El tipo de documento del pasajero no puede estar vacío.";
        }

        if (passenger.getDocumentNumber() == null || passenger.getDocumentNumber().trim().isEmpty()) {
            return "El número de documento del pasajero no puede estar vacío.";
        }
        if (passenger.getDocumentNumber().length() < 3 || passenger.getDocumentNumber().length() > 20) {
            return "El número de documento del pasajero debe tener entre 3 y 20 caracteres.";
        }
        if (!DOCUMENT_NUMBER_PATTERN.matcher(passenger.getDocumentNumber()).matches()) {
            return "El número de documento del pasajero solo debe contener caracteres alfanuméricos y guiones.";
        }

        return null;
    }
}