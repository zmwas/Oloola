package com.Oloola.Oloola.services;

import com.Oloola.Oloola.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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
}
