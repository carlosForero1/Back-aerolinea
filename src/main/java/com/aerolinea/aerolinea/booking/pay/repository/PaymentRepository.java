package com.aerolinea.aerolinea.booking.pay.repository;

import com.aerolinea.aerolinea.booking.pay.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
}
