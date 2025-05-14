package com.aerolinea.aerolinea.booking.tariff.controller;

import com.aerolinea.aerolinea.booking.tariff.entity.Additional;
import com.aerolinea.aerolinea.booking.tariff.entity.Tariff;
import com.aerolinea.aerolinea.booking.tariff.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/tariffs/{id}")
    public List<Tariff> getTariffByFlightId(@PathVariable Long id){
        List<Tariff> todasLasTarifas = service.getAllTariff();
        List<Tariff> result = new ArrayList<>();

        if (!todasLasTarifas.isEmpty()){
            for (Tariff tariff:todasLasTarifas ) {
                if (tariff.getFlight().getId().equals(id)){
                    Set<Long> includedServiceIds = tariff.getIncludedServices().stream()
                            .map(Additional::getId)
                            .collect(Collectors.toSet());

                    for (Additional additional : tariff.getAdditionals()) {
                        if (includedServiceIds.contains(additional.getId())) {
                            additional.setAvailable(false);
                        }
                    }
                    result.add(tariff);
                }
            }
        }
        return result;
    }

    @GetMapping("/tariffs/{tariffId}/additionals")
    public ResponseEntity<List<Additional>> getAdditionalsForTariff(@PathVariable Long tariffId) {
        try {
            List<Tariff> taff = service.getAllTariff();
            List<Additional> additionals = service.getTariffWithAdditionals(tariffId);
            return new ResponseEntity<>(additionals, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
