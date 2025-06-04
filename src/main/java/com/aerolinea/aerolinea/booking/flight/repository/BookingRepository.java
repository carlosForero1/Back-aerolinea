package com.aerolinea.aerolinea.booking.flight.repository;

import com.aerolinea.aerolinea.booking.flight.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}