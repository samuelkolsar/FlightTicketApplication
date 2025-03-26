package com.flightticketapplication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;


public class SeatId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "seat_number")
    private int seatNumber;


    public SeatId() {}
    public SeatId(Flight flight, int seatNumber) {
        this.flight = flight;
        this.seatNumber = seatNumber;
    }
}
