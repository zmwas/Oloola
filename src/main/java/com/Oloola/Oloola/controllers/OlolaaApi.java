package com.Oloola.Oloola.controllers;

import com.Oloola.Oloola.dto.*;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Trip;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.responses.AuthResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("api/v1")
public interface OlolaaApi {
    @RequestMapping(value = "auth/signup",
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
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    ResponseEntity<Trip> createBooking(
            @RequestPart("file") MultipartFile file,
            CreateBookingDTO body

    );

    @RequestMapping(value = "/booking",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Trip>> fetchBookings(
    );

    @RequestMapping(value = "/booking/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Trip>> fetchBooking(
            @PathVariable Long id
    );

    @RequestMapping(value = "/truck",
            produces = {"application/json"},
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    ResponseEntity<Truck> createTruck(
            @RequestPart("photo") MultipartFile truckPhoto,
            @RequestPart("sticker") MultipartFile insuranceSticker,
            CreateTruckDTO body

    );

    @RequestMapping(value = "/driver",
            produces = {"application/json"},
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    ResponseEntity<Driver> createDriver(
            @RequestPart("photo") MultipartFile file,
            CreateDriverDTO body
    );

    @RequestMapping(value = "/driver",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Driver>> fetchDrivers(
    );

    @RequestMapping(value = "/truck",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Truck>> fetchTrucks(
    );

    @RequestMapping(value = "/filterTrip",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<List<Trip>> fetchTripsForLocation(
            @RequestBody FilterTripsDTO body
    );

    @RequestMapping(value = "/user",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<AppUser> updateFirebaseToken(
            @RequestBody UpdateFirebaseTokenDTO body
    );

    @RequestMapping(value = "/trip",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Trip> updatePrice(
            @RequestBody UpdatePriceDTO body
    );

    @RequestMapping(value = "/send",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> sendNotification(
            String firebaseToken, String message
    );

    @RequestMapping(value = "/downloads/{fileName:.+}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName, HttpServletRequest request
    );

}
