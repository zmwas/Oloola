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
public class CreateTruckDTO {
    String licensePlateNumber;

    String driverId;

    boolean isTrailer;

    Double availableTonage;

    String photoUrl;

    String insuranceSticker;

    String ntsaCertificateNumber;

    public Truck from(Driver driver) {
        Truck truck = new Truck();
        truck.setDriver(driver);
        truck.setAvailableTonage(availableTonage);
        truck.setInsuranceSticker(insuranceSticker);
        truck.setLicensePlateNumber(licensePlateNumber);
        truck.setNtsaCertificateNumber(ntsaCertificateNumber);
        truck.setTrailer(isTrailer);
        truck.setPhotoUrl(photoUrl);
        return truck;
    }
}
