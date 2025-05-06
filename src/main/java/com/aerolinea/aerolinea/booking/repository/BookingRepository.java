package com.aerolinea.aerolinea.booking.repository;

import com.aerolinea.aerolinea.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    int countByFlightId(Long flightId);
}