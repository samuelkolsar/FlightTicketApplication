package com.flightticketapplication.services;

import com.flightticketapplication.entities.Airport;
import com.flightticketapplication.entities.Flight;
import com.flightticketapplication.entities.Seat;
import com.flightticketapplication.entities.SeatId;
import com.flightticketapplication.repositories.AirportRepository;
import com.flightticketapplication.repositories.FlightRepository;
import com.flightticketapplication.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final AirportRepository airportRepository;

    // Standard seat quantities
    private static final int FIRST_CLASS_SEATS = 10;
    private static final int BUSINESS_CLASS_SEATS = 20;
    private static final int ECONOMY_CLASS_SEATS = 150;

    public FlightService(FlightRepository flightRepository, SeatRepository seatRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.airportRepository = airportRepository;
    }

    public Flight createRandomFlight() {
        Random random = new Random();
        List<Airport> airports = airportRepository.findAll();
        if (airports.size() < 2) throw new IllegalStateException("Not enough airports in the database!");

        Airport startAirport = airports.get(random.nextInt(airports.size()));
        Airport endAirport;
        do {
            endAirport = airports.get(random.nextInt(airports.size()));
        } while (startAirport.equals(endAirport));

        // Departing time is in the next 5 months and arrival time is 2-8 hours after the departure
        LocalDateTime departureTime = LocalDateTime.now().plusDays(random.nextInt(30)).plusMonths(random.nextInt(4));
        LocalDateTime arrivalTime = departureTime.plusHours(2 + random.nextInt(6));

        // Price is generated between 50 and 550
        double price = 50 + (random.nextDouble() * 500);


        return Flight.builder().departureTime(departureTime).arrivalTime(arrivalTime).startPoint(startAirport).endPoint(endAirport).price(price).build();
    }

    public void addSeatsToFlight(Flight flight) {
        Flight savedFlight = flightRepository.save(flight);
        generateSeatsForFlight(savedFlight);

    }

    private void generateSeatsForFlight(Flight flight) {
        List<Seat> seats = new ArrayList<>();
        int seatNumber = 1;

        // Create First-Class Seats
        // Single seats - Window seat + extra legroom
        for (int i = 1; i <= FIRST_CLASS_SEATS; i++) {
            SeatId seatId = new SeatId(flight.getId(), i);
            seats.add(Seat.builder().seatClass(1).isReserved(false).id(seatId).windowSeat(true).extraLegroom(true).closeToExit(false).build());
        }

        // Create Business-Class Seats
        // Always have extra legroom
        // Last row is near an exit
        boolean nearExit = false;
        int i = FIRST_CLASS_SEATS+1;
        int rows = BUSINESS_CLASS_SEATS / 4;
        for (int row = 0; row < rows; row++) {
            if (row == rows-1) nearExit = true;
            for (int j = 0; j < 4; j++) {
                SeatId seatId = new SeatId(flight.getId(), i);
                if (j==0 || j==3) seats.add(Seat.builder().seatClass(2).isReserved(false).id(seatId).extraLegroom(true).windowSeat(true).closeToExit(nearExit).build());
                else seats.add(Seat.builder().seatClass(2).isReserved(false).id(seatId).extraLegroom(true).windowSeat(false).closeToExit(nearExit).build());
                i++;
            }
        }

        // Create Economy-Class Seats
        // Rows of 6, no one has extra leg room.
        rows = ECONOMY_CLASS_SEATS / 6;
        for (int row = 0; row < rows; row++) {
            if (row == rows - 1) nearExit = true;
            for (int j = 0; j < 6; j++) {
                SeatId seatId = new SeatId(flight.getId(), i);
                if (j==0 || j==5) seats.add(Seat.builder().seatClass(2).isReserved(false).id(seatId).extraLegroom(false).windowSeat(true).closeToExit(nearExit).build());
                else seats.add(Seat.builder().seatClass(2).isReserved(false).id(seatId).extraLegroom(false).windowSeat(false).closeToExit(nearExit).build());
                i++;
                nearExit = false;
            }
        }

        // Save all seats in the database
        seatRepository.saveAll(seats);
    }
}
