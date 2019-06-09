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
    @JsonView({Views.Trip.class, Views.BookingDetails.class})
    Long id;
    @JsonView({Views.Trip.class, Views.BookingDetails.class})

    @OneToOne
    Truck truck;
    @JsonView({Views.Trip.class, Views.BookingDetails.class})

    @OneToOne
    Driver driver;
    @JsonView({Views.Trip.class, Views.BookingDetails.class})

    @ManyToOne()
    @PrimaryKeyJoinColumn
    AppUser transporter;
    @JsonView({Views.Trip.class, Views.BookingDetails.class})

    LocalDate firstAvailableDate;
    @JsonView({Views.Trip.class, Views.BookingDetails.class})

    LocalDate lastAvailableDate;
    @JsonView({Views.Trip.class, Views.BookingDetails.class})

    Double availableTonage;
    @JsonView({Views.Trip.class})

    @OneToOne
    Location tripStart;
    @JsonView({Views.Trip.class})

    @OneToOne
    Location tripDestination;
    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    @ManyToOne()
    @PrimaryKeyJoinColumn
    AppUser cargoMover;
    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    String cargoType;
    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    @OneToOne
    Location collectionPoint;
    @JsonView({Views.Booking.class, Views.BookingDetails.class})
    @OneToOne
    Location dropOffPoint;
    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    Double transportFees;
    @JsonView({Views.Booking.class})

    Boolean isAvailable = true;
    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    Double units;
    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    Double weight;
    @JsonView({Views.Booking.class})

    Boolean isBooked = false;
    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    String cargoPictureUrl;

    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    LocalDate firstCollectionDate;

    @JsonView({Views.Booking.class, Views.BookingDetails.class})

    LocalDate lastCollectionDate;


}

