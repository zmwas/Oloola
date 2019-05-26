package com.Oloola.Oloola.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    Truck truck;

    @OneToOne
    Driver driver;

    @ManyToOne()
    @PrimaryKeyJoinColumn
    AppUser transporter;

    LocalDate firstAvailableDate;

    LocalDate lastAvailableDate;

    Double availableTonage;

    @OneToOne
    Location tripStart;

    @OneToOne
    Location tripDestination;

    @ManyToOne()
    @PrimaryKeyJoinColumn
    AppUser cargoMover;

    String cargoType;

    @OneToOne
    Location collectionPoint;

    @OneToOne
    Location dropOffPoint;

    Double transportFees;

    Boolean isAvailable = true;

    Double units;

    Double weight;

    Boolean isBooked = false;
    String cargoPictureUrl;
}

