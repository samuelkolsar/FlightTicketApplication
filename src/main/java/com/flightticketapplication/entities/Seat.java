package com.flightticketapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Seats")
public class Seat {

    @EmbeddedId
    private SeatId id;

    @Column(name = "seat_class")
    private int seatClass;

    @Column(name = "is_reserved")
    private boolean isReserved;

    @Column(name = "extra_legroom")
    private boolean extraLegroom;

    @Column(name = "close_to_exit")
    private boolean closeToExit;

    @Column(name = "window_seat")
    private boolean windowSeat;
}