package com.aerolinea.aerolinea.others.repository;


import com.aerolinea.aerolinea.others.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository  extends JpaRepository<DocumentType, Long> {
}
