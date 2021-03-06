package com.Oloola.Oloola.services;

import com.Oloola.Oloola.FileStorageProperties;
import com.Oloola.Oloola.dto.CreateTruckDTO;
import com.Oloola.Oloola.models.Driver;
import com.Oloola.Oloola.models.Truck;
import com.Oloola.Oloola.repository.DriverRepository;
import com.Oloola.Oloola.repository.TruckRepository;
import com.Oloola.Oloola.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
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
public class TruckService extends BaseService {
    private TruckRepository truckRepository;
    private DriverRepository driverRepository;

    public TruckService(TruckRepository truckRepository, DriverRepository driverRepository, UserRepository userRepository, FileStorageProperties fileStorageProperties) {
        super(userRepository, fileStorageProperties);
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
    }

    public Truck createTruck(MultipartFile photo, MultipartFile sticker, CreateTruckDTO createTruckDTO) {
        String stickerFileName = storeFile(sticker);
        String insuranceSticker = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/downloads/")
                .path(stickerFileName)
                .toUriString();
        String photoFileName = storeFile(photo);
        String photoUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/downloads/")
                .path(photoFileName)
                .toUriString();
        Driver driver = null;
        if (createTruckDTO.getDriverId()!=null) {
            driver = fetchDriver(Long.valueOf(createTruckDTO.getDriverId()));
        }
        Truck truck = createTruckDTO.from(driver, loggedInUser(), insuranceSticker, photoUrl);
        return truckRepository.save(truck);
    }

    public Driver fetchDriver(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        return driver.orElse(null);
    }


    public List<Truck> fetchTrucks() {
        return truckRepository.findByTransporter(loggedInUser());
    }
}
