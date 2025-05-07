package com.aerolinea.aerolinea.booking.Controller;

import com.aerolinea.aerolinea.booking.entity.Additional;
import com.aerolinea.aerolinea.booking.entity.Tariff;
import com.aerolinea.aerolinea.booking.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<Tariff> todasLasTarifas = service.getValidTariffsForFlight(id);
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

    @GetMapping("/{tariffId}/additionals")
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
