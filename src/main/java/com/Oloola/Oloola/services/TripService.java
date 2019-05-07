package com.Oloola.Oloola.services;

import com.Oloola.Oloola.dto.CreateBookingDTO;
import com.Oloola.Oloola.dto.CreateEmptyTripDTO;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@Validated
public class TripService {
    TripRepository tripRepository;
    DriverRepository driverRepository;
    TruckRepository truckRepository;
    UserRepository userRepository;
    LocationRepository locationRepository;

    @Autowired
    public TripService(TripRepository tripRepository, DriverRepository driverRepository, TruckRepository truckRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }


    public Trip saveEmptyTrip(CreateEmptyTripDTO createEmptyTripDTO) {
        Truck truck = fetchTruck(createEmptyTripDTO.getTruckId());
        Driver driver = fetchDriver(createEmptyTripDTO.getDriverId());
        AppUser transporter = fetchUser(1l);
        Location tripStart = fetchLocation(createEmptyTripDTO.getTripStart());
        Location destination = fetchLocation(createEmptyTripDTO.getTripDestination());
        Trip trip = createEmptyTripDTO.from(truck, driver, tripStart, destination, transporter);
        return tripRepository.save(trip);
    }

    public Trip saveTripBooking(CreateBookingDTO createBookingDTO) {
        Location collectionPoint = fetchLocation(createBookingDTO.getCollectionPoint());
        Location dropOffPoint = fetchLocation(createBookingDTO.getDropOffPoint());
        Trip emptyTrip = findEmptyTrip(createBookingDTO.getTripId());
        Trip trip = createBookingDTO.from(emptyTrip, collectionPoint, dropOffPoint);
        return tripRepository.save(trip);
    }

    private Trip findEmptyTrip(String id) {
        Optional<Trip> trip = tripRepository.findById(Long.valueOf(id));
        if (!trip.isPresent()) {
            throw new NotFoundException("Trip", String.valueOf(id));
        }
        return trip.get();
    }

    public List<Trip> findClosestTrips(Location collectionPoint) {
        List<Trip> results = new ArrayList<>();
//        List<GeoResult<Trip>> results = tripRepository.findWithinRadius(new Circle(collectionPoint.getPoint(), distance)
//                , collectionPoint.getPoint());
        return results;
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

    private AppUser fetchUser(Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new NotFoundException("User", String.valueOf(userId));
        }
        return user.get();
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
}
