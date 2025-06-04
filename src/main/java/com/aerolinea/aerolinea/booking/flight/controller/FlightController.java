package com.aerolinea.aerolinea.booking.flight.controller;

import com.aerolinea.aerolinea.booking.flight.entity.Flight;
import com.aerolinea.aerolinea.booking.flight.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/api")
    public List<Flight> getAllFlight() {
        List<Flight> result = flightService.allFlights();
        return result;
    }

    @GetMapping("/api/flights")
    public List<Flight> getFlightsWithCapacityStatus(
            @RequestParam Long originCityId,
            @RequestParam Long destinationCityId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String date
    ) {
        List<Flight> result = new ArrayList<>();
        if (date != null && !date.isEmpty()) {
            result = flightService.getFlights(originCityId, destinationCityId, date);
        } else {
            List<Flight> flights = flightService.allFlights();
            for (Flight flight : flights) {
                if (destinationCityId.equals(flight.getArrivalCity().getId())) {
                    if (originCityId.equals(flight.getDepartureCity().getId())) {
                        result.add(flight);
                    }
                }
            }
        }

        return result;
    }

    @GetMapping("/api/flightsFyId/{id}")
    public Optional<Flight> getFlightsWithCapacityStatus(@PathVariable Long id) {
        return flightService.findById(id);
    }


}