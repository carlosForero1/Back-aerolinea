package com.aerolinea.aerolinea.booking.Controller;

import com.aerolinea.aerolinea.booking.entity.Flight;
import com.aerolinea.aerolinea.booking.service.FlightService;
import com.aerolinea.aerolinea.others.entity.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/api")
    public ResponseEntity<List<FlightInfo>> getAllFlightsWithCapacityStatus() {
        List<Flight> flights = flightService.allFlights();
        List<FlightInfo> flightInfos = flights.stream()
                .map(flight -> new FlightInfo(flight, flightService.isFlightFull(flight.getId())))
                .collect(Collectors.toList());
        return new ResponseEntity<>(flightInfos, HttpStatus.OK);
    }

    @GetMapping("/api/flights")
    public ResponseEntity<List<FlightInfo>> getFlightsWithCapacityStatus(
            @RequestParam Long originCityId,
            @RequestParam Long destinationCityId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String date // Hacer el parámetro 'date' opcional
    ) {
        List<Flight> result = new ArrayList<>();
        if (date != null && !date.isEmpty() && !date.equals("")) {
            result = flightService.getFlights(originCityId, destinationCityId, date);
        } else {
            List<Flight> flights = flightService.allFlights();
            for (Flight flight : flights) {
                if (originCityId.equals(flight.getArrivalCity().getId())) {
                    if (destinationCityId.equals(flight.getDepartureCity().getId())) {
                        result.add(flight);
                    }
                }
            }
        }

        List<FlightInfo> flightInfos = result.stream()
                .map(flight -> new FlightInfo(flight, flightService.isFlightFull(flight.getId())))
                .collect(Collectors.toList());
        return new ResponseEntity<>(flightInfos, HttpStatus.OK);
    }

    // Clase auxiliar para transportar la información del vuelo y su estado de capacidad
    static class FlightInfo {
        private Flight flight;
        private boolean isFull;

        public FlightInfo(Flight flight, boolean isFull) {
            this.flight = flight;
            this.isFull = isFull;
        }

        public Flight getFlight() {
            return flight;
        }

        public boolean isFull() {
            return isFull;
        }
    }
}