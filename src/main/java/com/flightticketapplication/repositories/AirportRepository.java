package com.flightticketapplication.repositories;

import com.flightticketapplication.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Optional<Airport> findByCity(String city);


    @Query("SELECT a.id FROM Airport a WHERE a.city LIKE %:cityOrCountry% OR a.country LIKE %:cityOrCountry%")
    Integer findAirportIdByCityOrCountry(@Param("cityOrCountry") String cityOrCountry);

}