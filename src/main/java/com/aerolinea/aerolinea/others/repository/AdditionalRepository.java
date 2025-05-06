package com.aerolinea.aerolinea.others.repository;

import com.aerolinea.aerolinea.others.entity.Additional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional,Long> {
}
