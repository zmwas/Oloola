package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.Trip;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TripRepository extends PagingAndSortingRepository<Trip, Long> {

//    @Query(value = "SELECT * from Trip t WHERE ST_Within(:point, circle)=true AND t.firstAvailableDate >= now() AND t.lastAvailableDate <= now()", nativeQuery = true)
////    List<GeoResult<Trip>> findWithinRadius(Circle circle, Point point);

}
