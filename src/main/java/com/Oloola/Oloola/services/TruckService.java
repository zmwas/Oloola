package com.Oloola.Oloola.services;

import com.Oloola.Oloola.repository.DriverRepository;
import com.Oloola.Oloola.repository.TruckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@Transactional
@Validated
public class TruckService {
    TruckRepository truckRepository;
    DriverRepository driverRepository;

    public TruckService(TruckRepository truckRepository, DriverRepository driverRepository) {
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
    }
}
