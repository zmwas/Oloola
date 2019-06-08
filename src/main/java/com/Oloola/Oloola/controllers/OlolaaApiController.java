package com.Oloola.Oloola.controllers;

import com.Oloola.Oloola.Views;
import com.Oloola.Oloola.dto.*;
import com.Oloola.Oloola.models.*;
import com.Oloola.Oloola.responses.AuthResponse;
import com.Oloola.Oloola.services.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class OlolaaApiController implements OlolaaApi {

    private DriverService driverService;
    private TruckService truckService;
    private UserService userService;
    private TripService tripService;
    private PushNotificationService pushNotificationService;
    private BaseService baseService;

    public OlolaaApiController(DriverService driverService, TruckService truckService, UserService userService, TripService tripService, PushNotificationService pushNotificationService, BaseService baseService) {
        this.driverService = driverService;
        this.truckService = truckService;
        this.userService = userService;
        this.tripService = tripService;
        this.pushNotificationService = pushNotificationService;
        this.baseService = baseService;
    }

    @JsonView(Views.Public.class)
    @Override
    public ResponseEntity<AppUser> signUp(CreateUserDTO body) {
        AppUser appUser = userService.createUser(body);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @JsonView(Views.Public.class)
    @Override
    public ResponseEntity<AuthResponse> login(AuthenticationRequest body) {
        AuthResponse response = new AuthResponse();
        Auth auth = userService.authenticateUser(body);
        response.setSuccess(true);
        response.setAuth(auth);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @JsonView(Views.Trip.class)

    @Override
    public ResponseEntity<Trip> createEmptyTrip(CreateEmptyTripDTO body) {
        Trip trip = tripService.saveEmptyTrip(body);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @JsonView(Views.Trip.class)
    @Override
    public ResponseEntity<Trip> createBooking(MultipartFile photo, CreateBookingDTO body) {
        Trip trip = tripService.saveTripBooking(photo, body);
        String firebaseToken = trip.getTransporter().getFirebaseToken();
        String message = composeMessage(trip.getCargoMover(), "has booked an empty trip");
        sendNotification(firebaseToken, message);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @JsonView(Views.BookingDetails.class)
    @Override
    public ResponseEntity<List<Trip>> fetchBookings() {
        List<Trip> bookings = tripService.fetchBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Trip>> fetchBooking(Long id) {
        Trip booking = tripService.findTripById(id);
        return null;
    }

    @JsonView(Views.Truck.class)
    @Override
    public ResponseEntity<Truck> createTruck(MultipartFile photo, MultipartFile sticker, CreateTruckDTO body) {
        Truck truck = truckService.createTruck(photo, sticker, body);
        return new ResponseEntity<>(truck, HttpStatus.CREATED);
    }

    @JsonView(Views.Driver.class)
    @Override
    public ResponseEntity<Driver> createDriver(MultipartFile photo, CreateDriverDTO body) {
        Driver driver = driverService.registerDriver(photo, body);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @JsonView(Views.Driver.class)

    @Override
    public ResponseEntity<List<Driver>> fetchDrivers() {
        List<Driver> drivers = driverService.fetchDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @JsonView(Views.Truck.class)
    @Override
    public ResponseEntity<List<Truck>> fetchTrucks() {
        List<Truck> trucks = truckService.fetchTrucks();
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    @JsonView(Views.Trip.class)
    @Override
    public ResponseEntity<List<Trip>> fetchTripsForLocation(FilterTripsDTO body) {
        List<Trip> trips = tripService.findClosestTrips(body);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @JsonView(Views.Public.class)
    @Override
    public ResponseEntity<AppUser> updateFirebaseToken(UpdateFirebaseTokenDTO body) {
        AppUser appUser = userService.updateFirebaseToken(body);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @JsonView({Views.Trip.class, Views.Booking.class})
    @Override
    public ResponseEntity<Trip> updatePrice(UpdatePriceDTO body) {
        Trip trip = tripService.updatePrice(body);
        String firebaseToken = trip.getCargoMover().getFirebaseToken();
        String message = composeMessage(trip.getTransporter(), "has updated the price");
        sendNotification(firebaseToken, message);

        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> sendNotification(String firebaseToken, String message) {
        JSONObject body = new JSONObject();
        body.put("to", firebaseToken);
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "Ololaa Notification");
        notification.put("body", message);
        body.put("notification", notification);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = pushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = baseService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }


    private String composeMessage(AppUser appUser, String message) {
        return appUser.getCompanyName() + message;
    }
}
