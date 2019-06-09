package com.Oloola.Oloola.models;

import com.Oloola.Oloola.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Truck.class, Views.Driver.class})
    Long id;

    @JsonView({Views.Driver.class, Views.Trip.class, Views.BookingDetails.class})
    String name;

    @JsonView({Views.Truck.class, Views.Driver.class})
    String drivingLicense;
    @JsonView(Views.Driver.class)

    String drivingLicenseType;

    @JsonView({Views.Truck.class, Views.Driver.class})
    String idNumber;
    @JsonView({Views.Driver.class, Views.BookingDetails.class})

    String passPortPhotoUrl;
    @JsonView(Views.Driver.class)
    @OneToOne
    Truck truck;

    @JsonView(Views.Driver.class)
    @OneToOne
    AppUser transporter;
}
