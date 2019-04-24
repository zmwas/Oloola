package com.Oloola.Oloola.controllers;

import com.Oloola.Oloola.dto.*;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Trip;
import com.Oloola.Oloola.models.Truck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OlolaaApiController implements OlolaaApi {
    @Override
    public ResponseEntity<AppUser> signUp(CreateUserDTO body) {
        return null;
    }

    @Override
    public ResponseEntity<Trip> createEmptyTrip(CreateEmptyTripDTO body) {
        return null;
    }

    @Override
    public ResponseEntity<Trip> createBooking(CreateBookingDTO body) {
        return null;
    }

    @Override
    public ResponseEntity<Truck> createTruck(CreateTruckDTO body) {
        return null;
    }

    @Override
    public ResponseEntity<Driver> createDriver(CreateDriverDTO body) {
        return null;
    }
}
