package com.Oloola.Oloola.dto;

import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Location;
import com.Oloola.Oloola.models.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDTO {

    String cargoType;

    String collectionPoint;

    String dropOffPoint;

    String tripId;

    public Trip from(Trip trip, Location collectionPoint, Location dropOffPoint, AppUser cargoMover) {
        trip.setCollectionPoint(collectionPoint);
        trip.setDropOffPoint(dropOffPoint);
        trip.setCargoType(cargoType);
        trip.setCargoMover(cargoMover);
        trip.setIsBooked(true);
        return trip;
    }
}
