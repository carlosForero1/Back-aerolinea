package com.aerolinea.aerolinea.booking.Seat.entidad;

import com.aerolinea.aerolinea.booking.flight.entity.Flight;
import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_type")
    private String seatType;

    @JoinColumn(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "allowed_fare")
    private String allowedFare;

    @Column(name = "column_number")
    private Integer columnNumber;

    @Column(name = "is_accessible")
    private Boolean isAccessible;

    @Column(name = "row_label")
    private String rowLabel;

    public Seat() {
    }

    public Seat(Long id, String seatType, String status, Flight flight,
                String allowedFare, Integer columnNumber, Boolean isAccessible,
                String rowLabel) {
        this.id = id;
        this.seatType = seatType;
        this.status = status;
        this.flight = flight;
        this.allowedFare = allowedFare;
        this.columnNumber = columnNumber;
        this.isAccessible = isAccessible;
        this.rowLabel = rowLabel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getAllowedFare() {
        return allowedFare;
    }

    public void setAllowedFare(String allowedFare) {
        this.allowedFare = allowedFare;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Boolean getAccessible() {
        return isAccessible;
    }

    public void setAccessible(Boolean accessible) {
        isAccessible = accessible;
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatType='" + seatType + '\'' +
                ", status='" + status + '\'' +
                ", flightId=" + flight +
                ", allowedFare='" + allowedFare + '\'' +
                ", columnNumber=" + columnNumber +
                ", isAccessible=" + isAccessible +
                ", rowLabel='" + rowLabel + '\'' +
                '}';
    }
}
