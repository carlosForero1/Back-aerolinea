package com.aerolinea.aerolinea.booking.Seat.controlador;

import com.aerolinea.aerolinea.booking.Seat.entidad.Seat;
import com.aerolinea.aerolinea.booking.Seat.servicio.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:3000")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/flight/{flightId}")
    public List<Seat> getSeatsByFlight(@PathVariable Long flightId) {
        return seatService.getSeatsByFlight(flightId);
    }

    @PutMapping("/{seatId}/status")
    public Seat updateSeatStatus(
            @PathVariable Long seatId,
            @RequestParam String status
    ) {
        return seatService.updateSeatStatus(seatId, status);
    }
}
