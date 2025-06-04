package com.aerolinea.aerolinea.booking.Seat.repositorio;

import com.aerolinea.aerolinea.booking.Seat.entidad.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepositorio extends JpaRepository<Seat, Long> {
    List<Seat> findByFlightId(Long flightId);
}

