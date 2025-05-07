package com.aerolinea.aerolinea.booking.Controller;

import com.aerolinea.aerolinea.booking.entity.Booking;
import com.aerolinea.aerolinea.booking.entity.EmergencyContact;
import com.aerolinea.aerolinea.booking.entity.Passenger;
import com.aerolinea.aerolinea.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createBooking(@RequestBody Map<String, Object> bookingData) {
        try {
            Long flightId = Long.parseLong(bookingData.get("flight").toString());
            Long tariffId = Long.parseLong(bookingData.get("tariff").toString());

            Map<String, Object> mainPassengerData = (Map<String, Object>) bookingData.get("mainPassenger");
            Passenger mainPassenger = new Passenger();
            mainPassenger.setName(mainPassengerData.get("name").toString());
            mainPassenger.setDocumentType(mainPassengerData.get("documentType").toString());
            mainPassenger.setDocument(mainPassengerData.get("document").toString());
            mainPassenger.setAge(Integer.parseInt(mainPassengerData.get("age").toString()));
            mainPassenger.setNeedsSpecialAssistance((Boolean) mainPassengerData.get("needsSpecialAssistance"));
            mainPassenger.setSpecialAssistanceDescription(mainPassengerData.get("specialAssistanceDescription").toString());

            // Mapear los pasajeros adicionales
            List<Map<String, Object>> additionalPassengersDataList = (List<Map<String, Object>>) bookingData.get("additionalPassengers");
            List<Passenger> additionalPassengers = new ArrayList<>();
            if (additionalPassengersDataList != null) {
                for (Map<String, Object> passengerData : additionalPassengersDataList) {
                    Passenger passenger = new Passenger();
                    passenger.setName(passengerData.get("name").toString());
                    passenger.setDocumentType(passengerData.get("documentType").toString());
                    passenger.setDocument(passengerData.get("document").toString());
                    passenger.setAge(Integer.parseInt(passengerData.get("age").toString()));
                    passenger.setNeedsSpecialAssistance((Boolean) passengerData.get("needsSpecialAssistance"));
                    passenger.setSpecialAssistanceDescription(passengerData.get("specialAssistanceDescription").toString());
                    additionalPassengers.add(passenger);
                }
            }

            // Mapear el contacto de emergencia
            EmergencyContact emergencyContact = null;
            if (bookingData.containsKey("emergencyContact") && bookingData.get("emergencyContact") != null) {
                Map<String, Object> emergencyContactData = (Map<String, Object>) bookingData.get("emergencyContact");
                emergencyContact = new EmergencyContact();
                emergencyContact.setName(emergencyContactData.get("name").toString());
                emergencyContact.setPhone(emergencyContactData.get("phone").toString());
            }

            // Obtener los IDs de los extras seleccionados
            List<Number> selectedExtrasNumbers = (List<Number>) bookingData.get("selectedExtras");
            List<Long> selectedExtras = new ArrayList<>();
            if (selectedExtrasNumbers != null) {
                for (Number number : selectedExtrasNumbers) {
                    selectedExtras.add(number.longValue());
                }
            }

            Double totalPrice = Double.parseDouble(bookingData.get("totalPrice").toString());

            Booking booking = bookingService.createBooking(flightId, tariffId, mainPassenger, additionalPassengers, emergencyContact, selectedExtras, totalPrice);

            return new ResponseEntity<>(Map.of("bookingId", booking.getBookingId(), "message", "Reserva creada con éxito."), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Error al crear la reserva: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}