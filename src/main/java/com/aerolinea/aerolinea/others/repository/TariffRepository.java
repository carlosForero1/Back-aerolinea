package com.aerolinea.aerolinea.others.repository;

import com.aerolinea.aerolinea.others.entity.DocumentType;
import com.aerolinea.aerolinea.others.entity.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
    List<Tariff> findByFlightId(Long flightId);
    List<Tariff> findByFlightIdAndIdIn(Long flightId, List<Long> ids);
    @Query("SELECT t FROM Tariff t LEFT JOIN FETCH t.Additionals WHERE t.id = :tariffId")
    Optional<Tariff> findTariffWithAdditionals(@Param("tariffId") Long tariffId);
}

