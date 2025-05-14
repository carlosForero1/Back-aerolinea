package com.aerolinea.aerolinea.booking.city.repository;

import com.aerolinea.aerolinea.booking.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
