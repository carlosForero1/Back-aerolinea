package com.aerolinea.aerolinea.booking.service;

import com.aerolinea.aerolinea.booking.entity.Passenger;
import com.aerolinea.aerolinea.booking.repository.PassengerRepository;
import com.aerolinea.aerolinea.others.entity.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Optional<Passenger> findByDocumentTypeAndDocument(DocumentType documentType, String document) {
        return passengerRepository.findByDocumentTypeAndDocument(documentType, document);
    }
}
