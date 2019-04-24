package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruckRepository  extends JpaRepository<Truck, Long> {
    List<Truck> findByTransporter();
}
