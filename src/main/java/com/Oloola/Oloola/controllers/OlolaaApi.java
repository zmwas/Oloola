package com.Oloola.Oloola.controllers;

import com.Oloola.Oloola.dto.*;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Trip;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.responses.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/ololaa")
public interface OlolaaApi {
    @RequestMapping(value = "auth/signUp",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<AppUser> signUp(
            @RequestBody CreateUserDTO body

    );

    @RequestMapping(value = "auth/login",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<AuthResponse> login(
            @RequestBody AuthenticationRequest body

    );


    @RequestMapping(value = "/trip",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Trip> createEmptyTrip(
            @RequestBody CreateEmptyTripDTO body

    );

    @RequestMapping(value = "/booking",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Trip> createBooking(
            @RequestBody CreateBookingDTO body

    );

    @RequestMapping(value = "/truck",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Truck> createTruck(
            @RequestBody CreateTruckDTO body

    );

    @RequestMapping(value = "/driver",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Driver> createDriver(
            @RequestBody CreateDriverDTO body
    );

    @RequestMapping(value = "/driver",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Driver>> fetchDrivers(
    );

    @RequestMapping(value = "/truck",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Truck>> fetchTrucks(
    );

    @RequestMapping(value = "/trip",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Trip>> fetchTripsForLocation(
            @RequestParam String startLocation, @RequestParam String destination

    );
}
