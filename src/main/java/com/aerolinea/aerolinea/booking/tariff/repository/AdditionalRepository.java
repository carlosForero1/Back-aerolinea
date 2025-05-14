package com.aerolinea.aerolinea.booking.tariff.repository;

import com.aerolinea.aerolinea.booking.tariff.entity.Additional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Long> {
}
