package com.Oloola.Oloola.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FilterTripsDTO {
    String collectionPoint;
    String dropOffPoint;
    Double latitude;
    Double longitude;
}
