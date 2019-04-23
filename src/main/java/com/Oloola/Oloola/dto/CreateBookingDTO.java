package com.Oloola.Oloola.dto;

import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Location;
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
}
