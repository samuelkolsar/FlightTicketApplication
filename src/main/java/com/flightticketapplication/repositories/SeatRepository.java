package com.flightticketapplication.repositories;

import com.flightticketapplication.entities.Seat;
import com.flightticketapplication.entities.SeatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, SeatId> {
    Optional<Seat> findById_FlightIdAndId_SeatNumber(Long flightId, int seatNumber);
    List<Seat> findById_FlightId(Long flightId);
}