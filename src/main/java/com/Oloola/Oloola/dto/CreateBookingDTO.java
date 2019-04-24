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


    public Trip from(Location collectionPoint, Location dropOffPoint) {
        Trip trip = new Trip();
        trip.setCollectionPoint(collectionPoint);
        trip.setDropOffPoint(dropOffPoint);
        trip.setCargoType(cargoType);
        return trip;
    }
}
