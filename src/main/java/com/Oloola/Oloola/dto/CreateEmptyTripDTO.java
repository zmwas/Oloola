package com.Oloola.Oloola.dto;

import com.Oloola.Oloola.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmptyTripDTO {

    Long truckId;

    Long driverId;

    String firstAvailableDate;

    String lastAvailableDate;

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
        trip.setFirstAvailableDate(getDateFromString(firstAvailableDate));
        trip.setLastAvailableDate(getDateFromString(lastAvailableDate));
        trip.setAvailableTonage(availableTonage);
        trip.setIsBooked(false);
        return trip;
    }
    private LocalDate getDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate parsedDate = null;

        try {
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        return parsedDate;
    }

}
