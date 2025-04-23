package com.aerolinea.aerolinea.others.controller;

import com.aerolinea.aerolinea.others.entity.City;
import com.aerolinea.aerolinea.others.entity.Tariff;
import com.aerolinea.aerolinea.others.service.CityService;
import com.aerolinea.aerolinea.others.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TariffController {
    @Autowired
    private TariffService service;

    @GetMapping("/tariffs")
    public List<Tariff> getAllTariffs(){
        List<Tariff> result = service.getAllTariff();
        System.out.println(result);
        return service.getAllTariff();
    }

    @GetMapping("/tariffs/flight/{id}")
    public List<Tariff> getTariffByFlightId(@PathVariable Long id){
        List<Tariff> todasLasTarifas = service.getAllTariff();
        List<Tariff> result = new ArrayList<>();

        if (!todasLasTarifas.isEmpty()){
            for (Tariff tariff:todasLasTarifas ) {
                if (tariff.getFlight().getId().equals(id)){
                    result.add(tariff);
                }

            }
        }

        return result;
    }
}
