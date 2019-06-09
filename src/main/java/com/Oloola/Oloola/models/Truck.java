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

public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Truck.class, Views.Trip.class, Views.BookingDetails.class})

    Long id;

    @OneToOne
    @JsonView(Views.Truck.class)

    AppUser transporter;
    @JsonView({Views.Truck.class, Views.Driver.class, Views.BookingDetails.class})

    String licensePlateNumber;

    @OneToOne
    @JsonView(Views.Truck.class)

    Driver driver;
    @JsonView(Views.Truck.class)

    boolean isTrailer;
    @JsonView({Views.Truck.class})

    Double availableTonage;
    @JsonView({Views.Truck.class, Views.BookingDetails.class})

    String photoUrl;
    @JsonView({Views.Truck.class, Views.BookingDetails.class})

    String insuranceSticker;
    @JsonView(Views.Truck.class)

    String ntsaCertificateNumber;
    @JsonView({Views.Truck.class, Views.Trip.class, Views.BookingDetails.class})

    String truckType;

}
