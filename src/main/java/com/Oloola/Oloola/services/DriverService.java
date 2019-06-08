package com.Oloola.Oloola.services;

import com.Oloola.Oloola.FileStorageProperties;
import com.Oloola.Oloola.dto.CreateDriverDTO;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.repository.DriverRepository;
import com.Oloola.Oloola.repository.TruckRepository;
import com.Oloola.Oloola.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@Validated
public class DriverService extends BaseService {
    DriverRepository driverRepository;
    TruckRepository truckRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, TruckRepository truckRepository, UserRepository userRepository, FileStorageProperties fileStorageProperties) {
        super(userRepository, fileStorageProperties);
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
    }

    public Driver registerDriver(MultipartFile photo, CreateDriverDTO driverDTO) {
        String fileName = storeFile(photo);
        String passport = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/downloads/")
                .path(fileName)
                .toUriString();

        Truck truck = null;
        if (driverDTO.getTruckId()!=null) {
           truck  = fetchTruck(driverDTO.getTruckId());
        }
        AppUser transporter = loggedInUser();
        Driver driver = driverDTO.from(truck, transporter, passport);
        driver.setTruck(truck);
        return driverRepository.save(driver);
    }

    public Truck fetchTruck(Long truckId) {
        Optional<Truck> truck = truckRepository.findById(truckId);
        return truck.orElse(null);
    }

    public List<Driver> fetchDrivers() {
        return driverRepository.findByTransporter(loggedInUser());
    }

}
