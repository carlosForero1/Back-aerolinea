package com.aerolinea.aerolinea.others.service;

import com.aerolinea.aerolinea.booking.entity.Flight;
import com.aerolinea.aerolinea.others.entity.Additional;
import com.aerolinea.aerolinea.others.entity.Tariff;
import com.aerolinea.aerolinea.others.repository.AdditionalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdditionalService {

    @Autowired
    private AdditionalRepository repository;

    public Optional<Additional> getValidAdditionalFortariff(Long id) {
        Optional<Additional> flightOptional = repository.findById(id);
        return flightOptional;
    }
}
