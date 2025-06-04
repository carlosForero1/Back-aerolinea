package com.aerolinea.aerolinea.booking.flight.controller;


import com.aerolinea.aerolinea.booking.Seat.entidad.Seat;
import com.aerolinea.aerolinea.booking.Seat.repositorio.SeatRepositorio;
import com.aerolinea.aerolinea.booking.flight.entity.Booking;
import com.aerolinea.aerolinea.booking.flight.entity.Passenger;
import com.aerolinea.aerolinea.booking.flight.repository.BookingRepository;
import com.aerolinea.aerolinea.booking.flight.valiator.PassengerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID; // Para generar bookingReference

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000") // Asegúrate de que coincida con tu frontend
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatRepositorio seatRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            booking.setBookingReference(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            booking.setBookingDateTime(LocalDateTime.now());

            if (booking.getPassengers() == null || booking.getPassengers().isEmpty()) {
                return ResponseEntity.badRequest().body("Debe haber al menos un pasajero en la reserva.");
            }

            for (Passenger passenger : booking.getPassengers()) {
                passenger.setBooking(booking);

                String validationError = PassengerValidator.validatePassenger(passenger);
                if (validationError != null) {
                    return ResponseEntity.badRequest().body("Error de validación para el pasajero '" + passenger.getName() + "': " + validationError);
                }
            }

            for (Passenger passenger : booking.getPassengers()) {
                Long seatId = passenger.getSelectedSeatId();
                if (seatId == null) {
                    return ResponseEntity.badRequest().body("El pasajero " + passenger.getName() + " no tiene un asiento seleccionado.");
                }

                Seat seat = seatRepository.findById(seatId).orElse(null);

                if (seat == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El asiento con ID " + seatId + " no existe.");
                }
                if (!"available".equalsIgnoreCase(seat.getStatus())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El asiento " + seat.getId() + " ya no está disponible.");
                }

                seat.setStatus("occupied");
                seatRepository.save(seat);
            }

            Booking savedBooking = bookingRepository.save(booking);

            return ResponseEntity.ok("Reserva creada exitosamente con referencia: " + savedBooking.getBookingReference());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la reserva: " + e.getMessage());
        }
    }
}
