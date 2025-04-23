package com.aerolinea.aerolinea.others.repository;

import com.aerolinea.aerolinea.others.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
