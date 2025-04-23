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

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.allFlights();
    }

    @GetMapping("/api/flights")
    public List<Flight> getFlights(
            @RequestParam Long originCityId,
            @RequestParam Long destinationCityId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String date // Hacer el parámetro 'date' opcional
    ) {
        List<Flight> result = new ArrayList<>();
        if (date != null && !date.isEmpty() && !date.equals("")){
            result= flightService.getFlights(originCityId, destinationCityId, date);
        }else {
            List<Flight> flights = flightService.allFlights();

            for (Flight flight: flights) {
             if (originCityId.equals(flight.getArrivalCity().getId())){
                 if (destinationCityId.equals(flight.getDepartureCity().getId())){
                     result.add(flight);
                 }
             }
            }
        }

    return result;
    }


}
