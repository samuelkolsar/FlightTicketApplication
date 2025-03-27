package com.flightticketapplication.services;

import com.flightticketapplication.entities.Airport;
import com.flightticketapplication.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public String addAirport(String country, String city) {
        Optional<Airport> existingAirport = airportRepository.findByCity(city);

        if (existingAirport.isPresent()) {
            return "Airport with city " + city + " already exists!";
        } else {
            airportRepository.save(Airport.builder().country(country).city(city).build());
            return "New airport added: " + city;
        }
    }
}
