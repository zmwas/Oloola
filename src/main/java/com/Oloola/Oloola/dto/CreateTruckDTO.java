package com.Oloola.Oloola.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTruckDTO {
    String licensePlateNumber;

    String driverId;

    boolean isTrailer;

    Double availableTonage;

    String photoUrl;

    String insuranceSticker;

    String ntsaCertificateNumber;
}
