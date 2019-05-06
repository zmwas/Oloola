package com.Oloola.Oloola.services;

import com.Oloola.Oloola.dto.CreateBookingDTO;
import com.Oloola.Oloola.dto.CreateEmptyTripDTO;
import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.*;
import com.Oloola.Oloola.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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
        List<Trip> trips = new ArrayList<>();

        return trips;
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
        Location location = new Location();
        location.setName(name);
        return locationRepository.save(location);
    }
}
