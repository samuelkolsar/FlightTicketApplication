package com.flightticketapplication.controller;

import com.flightticketapplication.entities.Flight;
import com.flightticketapplication.entities.Seat;
import com.flightticketapplication.repositories.AirportRepository;
import com.flightticketapplication.repositories.SeatRepository;
import com.flightticketapplication.services.AirportService;
import com.flightticketapplication.services.FlightService;
import com.flightticketapplication.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private SeatService seatService;  // Autowire seatService instead of instantiating it manually

    @GetMapping
    public List<Flight> getFlights(
            @RequestParam(required = false) String fromCity,
            @RequestParam(required = false) String toCity,
            @RequestParam(required = false) String selectedClass,
            @RequestParam(required = false) Integer travellers,
            @RequestParam(required = false) String departure) {
        System.out.println(fromCity);
        System.out.println(toCity);
        System.out.println(departure);
        LocalDateTime departureDateTimeStart = null;
        LocalDateTime departureDateTimeEnd = null;


        if (departure != null && !departure.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(departure, formatter);

            departureDateTimeStart = LocalDateTime.of(date, LocalTime.MIDNIGHT);
            departureDateTimeEnd = departureDateTimeStart.with(LocalTime.MAX);
        }

        int classNumber = 0;
        if (selectedClass.equals("Economy Class")) classNumber = 3;
        if (selectedClass.equals("Business Class")) classNumber = 2;
        if (selectedClass.equals("First Class")) classNumber = 1;

        // Checking if there are enough seats available in the correct travel class
        var result = flightService.getFlights(fromCity, toCity, departureDateTimeStart, departureDateTimeEnd);

        List<Flight> filteredFlights = new ArrayList<>(result);
        for (Flight flight : result) {
            System.out.println(flight.getId());
            List<Seat> viableSeatsOnFlight = seatRepository.findById_FlightIdAndIsReservedFalseAndSeatClass(flight.getId(), classNumber);
            if (viableSeatsOnFlight.size() < travellers) {
                filteredFlights.remove(flight);
            }
        }
        return filteredFlights;
    }
    @GetMapping("/seats")
    public List<Seat> getSeatsForFlight(
            @RequestParam Integer flightId,
            @RequestParam Integer travelClass
    ) {
        return seatRepository.findById_FlightIdAndSeatClass(flightId, travelClass);
    }
    @PostMapping("/book")
    public void bookSeats(
            @RequestParam Integer flightId,
            @RequestBody Integer[] seatIds
    ) {
        for (Integer seatId : seatIds) {
            seatService.reserveSeat((long) flightId, seatId);
        }
        System.out.println("Seats booked!");
    }
}
