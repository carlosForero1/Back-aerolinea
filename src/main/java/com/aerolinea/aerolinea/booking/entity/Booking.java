package com.aerolinea.aerolinea.booking.entity;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long flightId;
    private Long tariffId;

    @ManyToOne
    @JoinColumn(name = "main_passenger_id")
    private Passenger mainPassenger;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> additionalPassengers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emergency_contact_id")
    private EmergencyContact emergencyContact;

    @ElementCollection
    private List<Long> selectedExtras;
    private Double totalPrice;

    public Booking() {
    }

    public Booking(Long id, Long flightId, Long tariffId, Passenger mainPassenger,
                   List<Passenger> additionalPassengers, List<Long> selectedExtras,
                   Double totalPrice) {
        this.id = id;
        this.flightId = flightId;
        this.tariffId = tariffId;
        this.mainPassenger = mainPassenger;
        this.additionalPassengers = additionalPassengers;
        this.selectedExtras = selectedExtras;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
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

    public List<Long> getSelectedExtras() {
        return selectedExtras;
    }

    public void setSelectedExtras(List<Long> selectedExtras) {
        this.selectedExtras = selectedExtras;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", tariffId=" + tariffId +
                ", mainPassenger=" + mainPassenger +
                ", additionalPassengers=" + additionalPassengers +
                ", selectedExtras=" + selectedExtras +
                ", totalPrice=" + totalPrice +
                '}';
    }
}