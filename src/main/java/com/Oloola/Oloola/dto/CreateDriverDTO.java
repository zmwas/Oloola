package com.Oloola.Oloola.dto;

import com.Oloola.Oloola.models.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDriverDTO {
    String name;

    String drivingLicense;

    String drivingLicenseType;

    String idNumber;

    String passPortPhotoUrl;

    String truckId;

}
