package com.aerolinea.aerolinea.booking.flight.repository;

import com.aerolinea.aerolinea.booking.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.departureCity.id = :originCityId AND f.arrivalCity.id = :destinationCityId AND f.departureDate = :date")
    List<Flight> findBy(
            @Param("originCityId") Long originCityId,
            @Param("destinationCityId") Long destinationCityId,
            @Param("date") String date
    );
}