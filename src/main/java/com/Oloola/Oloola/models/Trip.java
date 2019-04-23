package com.Oloola.Oloola.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    Truck truck;

    Driver driver;

    AppUser transporter;

    AppUser cargoMover;

    LocalDate firstAvailableDate;

    LocalDate lastAvailableDate;

    String cargoType;

    Double availableTonage;

    Location tripStart;

    Location tripDestination;

    Location collectionPoint;

    Location dropOffPoint;

    Double transportFees;
}
