package com.flightticketapplication.services;

import com.flightticketapplication.entities.Seat;
import com.flightticketapplication.repositories.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    public void reserveSeat(Long flightId, int seatNumber) {
        Optional<Seat> seatOptional = seatRepository.findById_FlightIdAndId_SeatNumber(flightId, seatNumber);

        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            if (seat.isReserved()) {
                return;
            }

            seat.setReserved(true);
            seatRepository.save(seat);
            System.out.println("Reserved Seat successfully!");
        } else {
            System.out.println("Seat does not exist!");
        }
    }
    public List<Seat> findAllSeatsByFlightId(Long flightId) {
        return seatRepository.findById_FlightId(flightId);
    }
}
