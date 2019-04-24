package com.Oloola.Oloola.services;

import com.Oloola.Oloola.dto.CreateDriverDTO;
import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.repository.DriverRepository;
import com.Oloola.Oloola.repository.TruckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@Validated
public class DriverService {
    DriverRepository driverRepository;
    TruckRepository truckRepository;


    @Autowired
    public DriverService(DriverRepository driverRepository, TruckRepository truckRepository) {
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
    }

    public Driver registerDriver(CreateDriverDTO driverDTO) {
        Truck truck = fetchTruck(driverDTO.getTruckId());
        Driver driver = driverDTO.from(truck);
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
}
