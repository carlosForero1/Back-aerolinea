package com.aerolinea.aerolinea.others.entity;

import com.aerolinea.aerolinea.booking.entity.Flight;
import jakarta.persistence.*;

@Entity
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fare_condition", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private Double price;

    @Column(nullable = false)
    private String currency;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", flight=" + flight +
                '}';
    }
}
