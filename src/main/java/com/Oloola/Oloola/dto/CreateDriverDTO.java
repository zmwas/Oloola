package com.Oloola.Oloola.dto;

import com.Oloola.Oloola.models.Driver;
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

    Long truckId;

    public Driver from(Truck truck) {
        Driver driver = new Driver();
        driver.setTruck(truck);
        driver.setDrivingLicense(drivingLicense);
        driver.setDrivingLicenseType(drivingLicenseType);
        driver.setName(name);
        driver.setPassPortPhotoUrl(passPortPhotoUrl);
        driver.setIdNumber(idNumber);
        return driver;
    }


}
