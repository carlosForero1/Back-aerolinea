package com.aerolinea.aerolinea.booking.service;

import com.aerolinea.aerolinea.booking.entity.Flight;
import com.aerolinea.aerolinea.booking.repository.FlightRepository;
import com.aerolinea.aerolinea.booking.entity.Additional;
import com.aerolinea.aerolinea.booking.entity.Tariff;
import com.aerolinea.aerolinea.booking.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TariffService {
    @Autowired
    private TariffRepository repository;

    @Autowired
    private FlightRepository flightRepository;

    public List<Tariff> getAllTariff() {
        return repository.findAll();
    }

    public List<Tariff> getValidTariffsForFlight(Long flightId) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (flightOptional.isEmpty()) {
            return List.of();
        }
        Flight flight = flightOptional.get();
        String validRatesStr = flight.getValidRates();

        if (validRatesStr == null || validRatesStr.isEmpty()) {
            return repository.findByFlightId(flightId);
        }

        List<Long> validRateIds = Arrays.stream(validRatesStr.split("-"))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        return repository.findByFlightIdAndIdIn(flightId, validRateIds);
    }

    public List<Additional> getTariffWithAdditionals(Long tariffId) {
        Optional<Tariff> optionalTariff = repository.findTariffWithAdditionals(tariffId);
        if (optionalTariff.isPresent()) {
            return optionalTariff.get().getAdditionals();
        } else {
            throw new RuntimeException("Tariff not found with id: " + tariffId);
        }
    }
}
