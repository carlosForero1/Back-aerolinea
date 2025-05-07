package com.aerolinea.aerolinea.booking.service;

import com.aerolinea.aerolinea.booking.entity.*;
import com.aerolinea.aerolinea.booking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private TariffRepository tariffRepository;
    @Autowired
    private AdditionalRepository additionalRepository;

    @Transactional
    public Booking createBooking(Long flightId, Long tariffId, Passenger mainPassenger, List<Passenger> additionalPassengers, EmergencyContact emergencyContact, List<Long> selectedExtras, Double totalPrice) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        Optional<Tariff> tariffOptional = tariffRepository.findById(tariffId);

        if (flightOptional.isEmpty() || tariffOptional.isEmpty()) {
            throw new RuntimeException("Vuelo o tarifa no encontrados.");
        }

        Flight flight = flightOptional.get();
        Tariff tariff = tariffOptional.get();

        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setFlight(flight);
        booking.setTariff(tariff);
        booking.setTotalPrice(totalPrice);
        booking.setMainPassenger(mainPassenger);
        booking.setAdditionalPassengers(additionalPassengers);
        booking.setEmergencyContact(emergencyContact);

        List<Additional> extras = new ArrayList<>();
        if (selectedExtras != null) {
            for (Long extraId : selectedExtras) {
                Optional<Additional> additionalOptional = additionalRepository.findById(extraId);
                additionalOptional.ifPresent(extras::add);
            }
        }
        booking.setBookingExtras(extras);

        return bookingRepository.save(booking);
    }
}