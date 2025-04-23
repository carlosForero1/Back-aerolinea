package com.aerolinea.aerolinea.others.service;

import com.aerolinea.aerolinea.others.entity.City;
import com.aerolinea.aerolinea.others.entity.Tariff;
import com.aerolinea.aerolinea.others.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {
    @Autowired
    private TariffRepository repository;

    public List<Tariff> getAllTariff(){
        return repository.findAll();
    }
}
