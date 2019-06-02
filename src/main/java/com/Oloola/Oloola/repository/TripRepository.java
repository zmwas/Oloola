package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Trip;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM trip as t " +
                    "INNER JOIN location as l ON l.id = t.trip_start_id " +
                    "AND ST_DWithin(ST_MakePoint(:latitude,:longitude)::geography, ST_MakePoint(l.latitude, l.longitude)::geography, 15000) " +
                    "AND t.is_booked=false " +
                    "ORDER BY ST_Distance(ST_MakePoint(:latitude,:longitude)::geography, ST_MakePoint(l.latitude, l.longitude)::geography)")
    Optional<List<Trip>> filterTrips(Long collectionPointId, Long dropOffPointId, Double latitude, Double longitude);

    Optional<List<Trip>> findByIsBookedAndTransporter(Boolean isBooked, AppUser transporter);

    Optional<List<Trip>> findByIsBookedAndCargoMover(Boolean isBooked, AppUser cargoMover);

}
