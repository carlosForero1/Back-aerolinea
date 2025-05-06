package com.aerolinea.aerolinea.booking.service;

import com.aerolinea.aerolinea.booking.entity.Booking;
import com.aerolinea.aerolinea.booking.entity.Flight;
import com.aerolinea.aerolinea.booking.repository.BookingRepository;
import com.aerolinea.aerolinea.booking.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Transactional
    public Booking createBooking(Booking booking) {
        Flight flight = flightRepository.findById(booking.getFlightId())
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        int bookedSeats = bookingRepository.countByFlightId(booking.getFlightId());
        int totalPassengers = 1 + (booking.getAdditionalPassengers() != null ? booking.getAdditionalPassengers().size() : 0);

        if (bookedSeats + totalPassengers > flight.getCapacity()) {
            throw new IllegalStateException("La capacidad máxima del vuelo ha sido alcanzada.");
        }

        return bookingRepository.save(booking);
    }

    public int getBookedSeatsForFlight(Long flightId) {
        return bookingRepository.countByFlightId(flightId);
    }
}