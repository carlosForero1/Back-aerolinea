package com.aerolinea.aerolinea.booking.service;


import com.aerolinea.aerolinea.booking.entity.Flight;
import com.aerolinea.aerolinea.booking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository repository;


    public List<Flight> getFlights(Long originCityId, Long destinationCityId, String date) {
        return repository.findBy(originCityId, destinationCityId, date);
    }

    public List<Flight> allFlights() {
        return repository.findAll();
    }
    public Optional<Flight> findById(Long id) {
        return repository.findById(id);
    }


}


