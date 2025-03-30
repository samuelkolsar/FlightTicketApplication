package com.flightticketapplication.repositories;

import com.flightticketapplication.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByStartPoint_idAndEndPoint_idAndDepartureTimeBetween(
            int startPointId,
            int endPointId,
            LocalDateTime departureDateTimeStart,
            LocalDateTime departureDateTimeEnd);
}
