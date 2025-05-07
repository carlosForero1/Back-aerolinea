package com.aerolinea.aerolinea.booking.Controller;

import com.aerolinea.aerolinea.booking.entity.Flight;
import com.aerolinea.aerolinea.booking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/api")
    public List<Flight> getAllFlight(  ) {
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

}