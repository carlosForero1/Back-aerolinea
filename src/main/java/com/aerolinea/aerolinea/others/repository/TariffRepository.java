package com.aerolinea.aerolinea.others.repository;

import com.aerolinea.aerolinea.others.entity.DocumentType;
import com.aerolinea.aerolinea.others.entity.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
