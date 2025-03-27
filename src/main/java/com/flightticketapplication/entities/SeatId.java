package com.flightticketapplication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SeatId implements Serializable {
    @Column(name = "flight_id")
    private long flightId;

    @Column(name = "seat_number")
    private int seatNumber;

}
