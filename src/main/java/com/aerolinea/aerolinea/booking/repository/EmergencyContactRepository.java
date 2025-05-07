package com.aerolinea.aerolinea.booking.repository;

import com.aerolinea.aerolinea.booking.entity.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
}
