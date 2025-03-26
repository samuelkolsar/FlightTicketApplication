package com.flightticketapplication;

import com.flightticketapplication.entities.Flight;
import com.flightticketapplication.repositories.FlightRepository;
import com.flightticketapplication.services.FlightService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class FlightTicketApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FlightTicketApplication.class, args);
        var repository = context.getBean(FlightRepository.class);
        var flightService = context.getBean(FlightService.class);


        repository.deleteById(1);
        for (int i = 0; i < 5; i++) {
            var flight = flightService.createRandomFlight();
            flightService.addSeatsToFlight(flight);
        }
    }
}
