package com.aerolinea.aerolinea.others.controller;

import com.aerolinea.aerolinea.others.entity.City;
import com.aerolinea.aerolinea.others.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/api/cities/origin-cities")
    public List<City> getOriginCities(){
        return cityService.getOriginCities();
    }

    @GetMapping("/api/cities/destination-cities/{cityId}")
    public List<City> getDestinationCityByOriginCity(@PathVariable Long cityId){
        List<City> result = cityService.getDestinationCityByOriginCity(cityId);
        return result;
    }
}
