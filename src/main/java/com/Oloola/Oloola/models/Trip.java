package com.Oloola.Oloola.models;

import com.Oloola.Oloola.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Trip.class})
    Long id;
    @JsonView({Views.Trip.class})

    @OneToOne
    Truck truck;
    @JsonView({Views.Trip.class})

    @OneToOne
    Driver driver;
    @JsonView({Views.Trip.class})

    @ManyToOne()
    @PrimaryKeyJoinColumn
    AppUser transporter;
    @JsonView({Views.Trip.class})

    LocalDate firstAvailableDate;
    @JsonView({Views.Trip.class})

    LocalDate lastAvailableDate;
    @JsonView({Views.Trip.class})

    Double availableTonage;
    @JsonView({Views.Trip.class})

    @OneToOne
    Location tripStart;
    @JsonView({Views.Trip.class})

    @OneToOne
    Location tripDestination;
    @JsonView({Views.Booking.class})

    @ManyToOne()
    @PrimaryKeyJoinColumn
    AppUser cargoMover;
    @JsonView({Views.Booking.class})

    String cargoType;
    @JsonView({Views.Booking.class})

    @OneToOne
    Location collectionPoint;
    @JsonView({Views.Booking.class})
    @OneToOne
    Location dropOffPoint;
    @JsonView({Views.Booking.class})

    Double transportFees;
    @JsonView({Views.Booking.class})

    Boolean isAvailable = true;
    @JsonView({Views.Booking.class})

    Double units;
    @JsonView({Views.Booking.class})

    Double weight;
    @JsonView({Views.Booking.class})

    Boolean isBooked = false;
    @JsonView({Views.Booking.class})

    String cargoPictureUrl;
}

