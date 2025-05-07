package com.aerolinea.aerolinea.booking.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingId;
    @ManyToOne
    private Flight flight;
    @ManyToOne
    private Tariff tariff;
    private Double totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    private Passenger mainPassenger;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Passenger> additionalPassengers;
    @OneToOne(cascade = CascadeType.ALL)
    private EmergencyContact emergencyContact;
    @ManyToMany
    private List<Additional> bookingExtras;

    public Booking() {
    }

    public Booking(Long id, String bookingId, Flight flight, Tariff tariff, Double totalPrice, Passenger mainPassenger,
                   List<Passenger> additionalPassengers, EmergencyContact emergencyContact, List<Additional> bookingExtras) {
        this.id = id;
        this.bookingId = bookingId;
        this.flight = flight;
        this.tariff = tariff;
        this.totalPrice = totalPrice;
        this.mainPassenger = mainPassenger;
        this.additionalPassengers = additionalPassengers;
        this.emergencyContact = emergencyContact;
        this.bookingExtras = bookingExtras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Passenger getMainPassenger() {
        return mainPassenger;
    }

    public void setMainPassenger(Passenger mainPassenger) {
        this.mainPassenger = mainPassenger;
    }

    public List<Passenger> getAdditionalPassengers() {
        return additionalPassengers;
    }

    public void setAdditionalPassengers(List<Passenger> additionalPassengers) {
        this.additionalPassengers = additionalPassengers;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public List<Additional> getBookingExtras() {
        return bookingExtras;
    }

    public void setBookingExtras(List<Additional> bookingExtras) {
        this.bookingExtras = bookingExtras;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingId='" + bookingId + '\'' +
                ", flight=" + flight +
                ", tariff=" + tariff +
                ", totalPrice=" + totalPrice +
                ", mainPassenger=" + mainPassenger +
                ", additionalPassengers=" + additionalPassengers +
                ", emergencyContact=" + emergencyContact +
                ", bookingExtras=" + bookingExtras +
                '}';
    }
}