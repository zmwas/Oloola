package com.Oloola.Oloola.services;

import com.Oloola.Oloola.dto.CreateTruckDTO;
import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.repository.DriverRepository;
import com.Oloola.Oloola.repository.TruckRepository;
import com.Oloola.Oloola.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@Validated
public class TruckService extends BaseService{
    private TruckRepository truckRepository;
    private DriverRepository driverRepository;

    public TruckService(TruckRepository truckRepository, DriverRepository driverRepository, UserRepository userRepository) {
        super(userRepository);
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
    }

    public Truck createTruck(CreateTruckDTO createTruckDTO) {
        Driver driver = fetchDriver(Long.valueOf(createTruckDTO.getDriverId()));
        Truck truck = createTruckDTO.from(driver, loggedInUser());
        return truckRepository.save(truck);
    }

    public Driver fetchDriver(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (!driver.isPresent()) {
            throw new NotFoundException("Driver", String.valueOf(id));
        }
        return driver.get();
    }


    public List<Truck> fetchTrucks() {
        return truckRepository.findByTransporter(loggedInUser());
    }
}
