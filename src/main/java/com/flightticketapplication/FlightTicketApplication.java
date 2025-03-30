package com.flightticketapplication;

import com.flightticketapplication.entities.Airport;
import com.flightticketapplication.repositories.AirportRepository;
import com.flightticketapplication.services.FlightService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class FlightTicketApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FlightTicketApplication.class, args);
        var flightService = context.getBean(FlightService.class);
        AirportRepository airportRepository = context.getBean(AirportRepository.class);

        // Adding airports if there aren't any.
        if (airportRepository.count() <= 1) {
            String[] countries = {
            "Estonia", "Latvia", "Spain", "Italy", "France",
                    "Portugal", "Sweden", "Hungary", "Austria", "Switzerland",
                    "United Arab Emirates", "Thailand", "Poland", "Croatia",
                    "Argentina", "Brazil", "Japan"};

            String[] capitals = {
                    "Tallinn", "Riga", "Madrid", "Naples", "Paris",
                    "Lisbon", "Stockholm", "Budapest", "Vienna", "Bern",
                    "Dubai", "Bangkok", "Warsaw", "Zagreb",
                    "Buenos Aires", "Rio de Janeiro", "Tokyo"
            };
            for (int i = 0; i < countries.length; i++) {
                airportRepository.save(Airport.builder().country(countries[i]).city(capitals[i]).build());
            }
        }
        // If the database is empty, there will be 1000 flight generated.
        if (!flightService.isThereFlights()){
            for (int i = 0; i < 1000; i++) {
                var flight = flightService.createRandomFlight();
                flightService.addSeatsToFlight(flight);
            }
        }
    }
}
