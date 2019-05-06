package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.Trip;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TripRepository extends PagingAndSortingRepository<Trip, Long> {

    @Query("SELECT t from Trip t WHERE within(circle, :point)=true AND t.firstAvailableDate >= now() AND t.lastAvailableDate >= now()")
    List<Trip> findWithinRadius(Circle circle, Point point);

}
