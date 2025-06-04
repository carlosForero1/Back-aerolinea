package com.aerolinea.aerolinea.booking.Seat.servicio;

import com.aerolinea.aerolinea.booking.Seat.entidad.Seat;
import com.aerolinea.aerolinea.booking.Seat.repositorio.SeatRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepositorio seatRepository;

    public List<Seat> getSeatsByFlight(Long flightId) {
        return seatRepository.findByFlightId(flightId);
    }

    public Seat updateSeatStatus(Long seatId, String newStatus) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        seat.setStatus(newStatus);
        return seatRepository.save(seat);
    }
}