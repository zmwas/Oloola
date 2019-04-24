package com.Oloola.Oloola.dto;

import com.Oloola.Oloola.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmptyTripDTO {

    Long truckId;

    Long driverId;

    LocalDate firstAvailableDate;

    LocalDate lastAvailableDate;

    Double availableTonage;

    String tripStart;

    String tripDestination;

    public Trip from(Truck truck, Driver driver, Location tripStart, Location destination, AppUser transporter) {
        Trip trip = new Trip();
        trip.setTransporter(transporter);
        trip.setTruck(truck);
        trip.setDriver(driver);
        trip.setTripStart(tripStart);
        trip.setTripDestination(destination);
        trip.setFirstAvailableDate(firstAvailableDate);
        trip.setLastAvailableDate(lastAvailableDate);
        trip.setAvailableTonage(availableTonage);
        return trip;
    }

}
