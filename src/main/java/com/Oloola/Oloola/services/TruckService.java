package com.Oloola.Oloola.services;

import com.Oloola.Oloola.dto.CreateTruckDTO;
import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.repository.DriverRepository;
import com.Oloola.Oloola.repository.TruckRepository;
import com.Oloola.Oloola.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@Validated
public class TruckService {
    TruckRepository truckRepository;
    DriverRepository driverRepository;
    UserRepository userRepository;

    public TruckService(TruckRepository truckRepository, DriverRepository driverRepository, UserRepository userRepository) {
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    }

    public Truck createTruck(CreateTruckDTO createTruckDTO) {
        Driver driver = fetchDriver(Long.valueOf(createTruckDTO.getDriverId()));
        AppUser transporter = findTransporter(Long.valueOf(createTruckDTO.getTransporterId()));
        Truck truck = createTruckDTO.from(driver, transporter);
        return truckRepository.save(truck);
    }

    public Driver fetchDriver(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (!driver.isPresent()) {
            throw new NotFoundException("Driver", String.valueOf(id));
        }
        return driver.get();
    }

    private AppUser findTransporter(Long userId) {
        Optional<AppUser> appUser = userRepository.findById(userId);
        if (!appUser.isPresent()) {
            throw new NotFoundException("user", String.valueOf(userId));
        }
        return appUser.get();
    }

}
