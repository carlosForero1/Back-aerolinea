package com.aerolinea.aerolinea.booking.flight.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingReference; // Para un identificador legible de la reserva
    private String flightId; // El ID del vuelo al que pertenece la reserva
    private String tariffInfoName; // Nombre de la tarifa, por ejemplo "Economy Basic"
    private double totalPrice; // El precio total de la reserva

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> passengers = new ArrayList<>();

    private LocalDateTime bookingDateTime; // Fecha y hora de la reserva

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getTariffInfoName() {
        return tariffInfoName;
    }

    public void setTariffInfoName(String tariffInfoName) {
        this.tariffInfoName = tariffInfoName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
        if (passengers != null) {
            passengers.forEach(p -> p.setBooking(this)); // Asegura la relación bidireccional
        }
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }
}