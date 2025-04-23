package com.aerolinea.aerolinea.booking.repository;

import com.aerolinea.aerolinea.booking.entity.Passenger;
import com.aerolinea.aerolinea.others.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    public Optional<Passenger> findByDocumentTypeAndDocument(DocumentType documentType, String document);
}
