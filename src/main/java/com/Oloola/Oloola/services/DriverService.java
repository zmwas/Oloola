package com.Oloola.Oloola.services;

import com.Oloola.Oloola.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DriverService {
    DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
}
