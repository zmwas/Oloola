package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.Trip;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TripRepository extends PagingAndSortingRepository<Trip, Long> {
}
