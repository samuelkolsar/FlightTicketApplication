package com.flightticketapplication.entities;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Seats")
public class Seat {

    @JsonUnwrapped
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