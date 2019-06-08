package com.Oloola.Oloola.services;

import com.Oloola.Oloola.FileStorageProperties;
import com.Oloola.Oloola.dto.CreateBookingDTO;
import com.Oloola.Oloola.dto.CreateEmptyTripDTO;
import com.Oloola.Oloola.dto.FilterTripsDTO;
import com.Oloola.Oloola.dto.UpdatePriceDTO;
import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.*;
import com.Oloola.Oloola.repository.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
@Transactional
@Validated
public class TripService extends BaseService {
    TripRepository tripRepository;
    DriverRepository driverRepository;
    TruckRepository truckRepository;
    LocationRepository locationRepository;

    @Autowired
    public TripService(TripRepository tripRepository, DriverRepository driverRepository, TruckRepository truckRepository, UserRepository userRepository, LocationRepository locationRepository, FileStorageProperties fileStorageProperties) {
        super(userRepository, fileStorageProperties);
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
        this.locationRepository = locationRepository;
    }


    public Trip saveEmptyTrip(CreateEmptyTripDTO createEmptyTripDTO) {
        Truck truck = fetchTruck(createEmptyTripDTO.getTruckId());
        Driver driver = fetchDriver(createEmptyTripDTO.getDriverId());
        Location tripStart = fetchLocation(createEmptyTripDTO.getTripStart());
        Location destination = fetchLocation(createEmptyTripDTO.getTripDestination());
        Trip trip = createEmptyTripDTO.from(truck, driver, tripStart, destination, loggedInUser());
        return tripRepository.save(trip);
    }

    public Trip saveTripBooking(MultipartFile photo, CreateBookingDTO createBookingDTO) {
        String fileName = storeFile(photo);
        String photos = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/downloads/")
                .path(fileName)
                .toUriString();

        Location collectionPoint = fetchLocation(createBookingDTO.getCollectionPoint());
        Location dropOffPoint = fetchLocation(createBookingDTO.getDropOffPoint());
        Trip emptyTrip = findEmptyTrip(createBookingDTO.getTripId());
        Trip trip = createBookingDTO.from(emptyTrip, collectionPoint, dropOffPoint, loggedInUser() , photos);
        return tripRepository.save(trip);
    }


    public List<Trip> fetchBookings() {
        List<Trip> bookings = new ArrayList<>();
        if (loggedInUser().getRoles().get(0).equals("transporter")) {
            Optional<List<Trip>> book = tripRepository.findByIsBookedAndTransporter(true, loggedInUser());
            if (!book.isPresent()){
               return bookings;
            }
            bookings = book.get();
        } else {
            Optional<List<Trip>> book = tripRepository.findByIsBookedAndCargoMover(true, loggedInUser());
            if (!book.isPresent()){
                return bookings;
            }
            bookings = book.get();

        }
        return bookings;
    }

    private Trip findEmptyTrip(String id) {
        Optional<Trip> trip = tripRepository.findById(Long.valueOf(id));
        if (!trip.isPresent()) {
            throw new NotFoundException("Trip", String.valueOf(id));
        }
        return trip.get();
    }

    public List<Trip> findClosestTrips(FilterTripsDTO body) {
        GeometryFactory geometryFactory = new GeometryFactory();
        List<Trip> trips = new ArrayList<>();
        Location dropOffPoint = fetchLocation(body.getDropOffPoint());
        Optional<List<Trip>> results = tripRepository.filterTrips(body.getLatitude(), body.getLongitude(), dropOffPoint.getLongitude(), dropOffPoint.getLongitude());
        return results.orElse(trips);

    }

    public Trip updatePrice(UpdatePriceDTO body) {
        Trip trip = findEmptyTrip(body.getTripId());
        trip.setTransportFees(body.getPrice());
        return trip;
    }

    private Driver fetchDriver(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (!driver.isPresent()) {
            throw new NotFoundException("Driver", String.valueOf(id));
        }
        return driver.get();
    }


    private Truck fetchTruck(Long truckId) {
        Optional<Truck> truck = truckRepository.findById(truckId);
        if (!truck.isPresent()) {
            throw new NotFoundException("truck", String.valueOf(truckId));
        }
        return truck.get();
    }

    private Location fetchLocation(String name) {
        Optional<Location> location = locationRepository.findByName(name);
        return location.orElseGet(() -> createNewLocation(name));
    }

    private Location createNewLocation(String name) {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAi4p2tQS5Os5QovXlMdIKKhVH8WIUEEwA")
                .build();
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context,
                    name).await();
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results[0].addressComponents));

        Double latitude = results[0].geometry.location.lat;
        Double longitude = results[0].geometry.location.lng;
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(latitude, longitude));
        Location location = new Location();
        location.setName(name);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setCoordinates(point);
        locationRepository.save(location);
        System.out.println(locationRepository.findByName(name).get().getCoordinates());
        return locationRepository.findByName(name).get();
    }

    public Trip findTripById(Long id) {
        Optional<Trip> trip = tripRepository.findById(id);
        if (!trip.isPresent()) {
            throw new NotFoundException("Trip", String.valueOf(id));
        }
        return trip.get();
    }
}
