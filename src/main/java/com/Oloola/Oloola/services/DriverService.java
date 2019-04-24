package com.Oloola.Oloola.services;

import com.Oloola.Oloola.dto.CreateDriverDTO;
import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.repository.DriverRepository;
import com.Oloola.Oloola.repository.TruckRepository;
import com.Oloola.Oloola.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@Validated
public class DriverService {
    DriverRepository driverRepository;
    TruckRepository truckRepository;
    UserRepository userRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, TruckRepository truckRepository, UserRepository userRepository) {
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
        this.userRepository = userRepository;
    }

    public Driver registerDriver(CreateDriverDTO driverDTO) {
        Truck truck = fetchTruck(driverDTO.getTruckId());
        AppUser transporter = findTransporter(driverDTO.getTransporterId());
        Driver driver = driverDTO.from(truck, transporter);
        driver.setTruck(truck);
        return driverRepository.save(driver);
    }

    public Truck fetchTruck(Long truckId) {
        Optional<Truck> truck = truckRepository.findById(truckId);
        if (!truck.isPresent()) {
            throw new NotFoundException("truck", String.valueOf(truckId));
        }
        return truck.get();
    }

    private AppUser findTransporter(Long userId) {
        Optional<AppUser> appUser = userRepository.findById(userId);
        if (!appUser.isPresent()) {
            throw new NotFoundException("user", String.valueOf(userId));
        }
        return appUser.get();
    }
}
