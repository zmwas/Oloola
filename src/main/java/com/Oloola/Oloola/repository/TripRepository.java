package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Location;
import com.Oloola.Oloola.models.Trip;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM trip as t " +
                    "LEFT JOIN location as l ON l.id = t.trip_start_id " +
                    "LEFT JOIN location as k ON k.id = t.trip_destination_id " +
                    "WHERE ST_DWithin(cast(ST_MakePoint(l.latitude,l.longitude) AS geography), cast(ST_MakePoint(:latitude,:longitude)AS geography) ,10000) " +
                    "AND ST_DWithin(cast(ST_MakePoint(k.latitude,k.longitude) AS geography), cast(ST_MakePoint(:latitude,:longitude)AS geography) ,15000) " +
                    "ORDER BY ST_Distance(cast(ST_MakePoint(l.latitude,l.longitude)AS geography), cast(ST_MakePoint(:dropLat,:dropLon)AS geography))")
    Optional<List<Trip>> filterTrips(@Param("latitude") Double latitude, @Param("longitude") Double longitude,@Param("dropLat") Double dropLat, @Param("dropLon") Double dropLon);

    Optional<List<Trip>> findByIsBookedAndTransporter(Boolean isBooked, AppUser transporter);

    Optional<List<Trip>> findByIsBookedAndCargoMover(Boolean isBooked, AppUser cargoMover);

}
