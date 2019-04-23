package com.Oloola.Oloola.services;

import com.Oloola.Oloola.repository.DriverRepository;
import com.Oloola.Oloola.repository.TruckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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
}
