package com.aerolinea.aerolinea.booking.repository;

import com.aerolinea.aerolinea.booking.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
