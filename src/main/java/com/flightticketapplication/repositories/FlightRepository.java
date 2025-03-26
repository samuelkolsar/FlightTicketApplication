package com.flightticketapplication.repositories;

import com.flightticketapplication.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}