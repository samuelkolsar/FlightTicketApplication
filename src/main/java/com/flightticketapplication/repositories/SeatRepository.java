package com.flightticketapplication.repositories;

public interface SeatRepository extends org.springframework.data.jpa.repository.JpaRepository<com.flightticketapplication.entities.Seat, com.flightticketapplication.entities.SeatId> {
  }