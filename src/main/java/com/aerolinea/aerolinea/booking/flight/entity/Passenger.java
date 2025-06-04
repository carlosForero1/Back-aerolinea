package com.aerolinea.aerolinea.booking.flight.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String documentType;
    private String documentNumber;
    private String specialAssistanceDescription;
    private Long selectedSeatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    // Getters y Setters
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getSpecialAssistanceDescription() {
        return specialAssistanceDescription;
    }

    public void setSpecialAssistanceDescription(String specialAssistanceDescription) {
        this.specialAssistanceDescription = specialAssistanceDescription;
    }

    public Long getSelectedSeatId() {
        return selectedSeatId;
    }

    public void setSelectedSeatId(Long selectedSeatId) {
        this.selectedSeatId = selectedSeatId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}