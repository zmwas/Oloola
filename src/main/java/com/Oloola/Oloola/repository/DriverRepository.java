package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByTransporter(AppUser transporter);
}
