package com.Oloola.Oloola.dto;

import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Location;
import com.Oloola.Oloola.models.Truck;
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

    String truckId;

    String driverId;

    LocalDate firstAvailableDate;

    LocalDate lastAvailableDate;

    Double availableTonage;

    String tripStart;

    String tripDestination;

}
