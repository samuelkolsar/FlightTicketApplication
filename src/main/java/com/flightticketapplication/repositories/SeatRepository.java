package com.flightticketapplication.repositories;

import com.flightticketapplication.entities.Seat;
import com.flightticketapplication.entities.SeatId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, SeatId> {
}