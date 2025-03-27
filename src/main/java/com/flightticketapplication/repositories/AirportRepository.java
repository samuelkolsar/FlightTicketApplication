package com.flightticketapplication.repositories;

import com.flightticketapplication.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    Optional<Airport> findByCity(String city);
}