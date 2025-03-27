package com.flightticketapplication.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;
}