package com.aerolinea.aerolinea.booking.city.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;



@Entity
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany
    private List<City> destinationCities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getDestinationCities() {
        return destinationCities;
    }

    public void setDestinationCities(List<City> destinationCities) {
        this.destinationCities = destinationCities;
    }
}