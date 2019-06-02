package com.Oloola.Oloola.dto;

import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Location;
import com.Oloola.Oloola.models.Trip;
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
public class CreateBookingDTO {

    String cargoType;

    String collectionPoint;

    String dropOffPoint;

    String tripId;

    Double units;

    Double weight;

    String firstCollectionDate;

    String lastCollectionDate;


    public Trip from(Trip trip, Location collectionPoint, Location dropOffPoint, AppUser cargoMover) {
        trip.setCollectionPoint(collectionPoint);
        trip.setDropOffPoint(dropOffPoint);
        trip.setCargoType(cargoType);
        trip.setCargoMover(cargoMover);
        trip.setUnits(units);
        trip.setWeight(weight);
        trip.setIsBooked(true);
        trip.setFirstCollectionDate(getDateFromString(firstCollectionDate));
        trip.setLastCollectionDate(getDateFromString(lastCollectionDate));
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
