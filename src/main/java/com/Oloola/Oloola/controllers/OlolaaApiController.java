package com.Oloola.Oloola.controllers;

import com.Oloola.Oloola.dto.*;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Trip;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.services.DriverService;
import com.Oloola.Oloola.services.TripService;
import com.Oloola.Oloola.services.TruckService;
import com.Oloola.Oloola.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OlolaaApiController implements OlolaaApi {

    DriverService driverService;
    TruckService truckService;
    UserService userService;
    TripService tripService;

    public OlolaaApiController(DriverService driverService, TruckService truckService, UserService userService, TripService tripService) {
        this.driverService = driverService;
        this.truckService = truckService;
        this.userService = userService;
        this.tripService = tripService;
    }

    @Override
    public ResponseEntity<AppUser> signUp(CreateUserDTO body) {
        AppUser appUser = userService.createUser(body);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Trip> createEmptyTrip(CreateEmptyTripDTO body) {
        Trip trip = tripService.saveEmptyTrip(body);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Trip> createBooking(CreateBookingDTO body) {
        Trip trip = tripService.saveTripBooking(body);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Truck> createTruck(CreateTruckDTO body) {
        Truck truck = truckService.createTruck(body);
        return new ResponseEntity<>(truck, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Driver> createDriver(CreateDriverDTO body) {
        Driver driver = driverService.registerDriver(body);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }
}
