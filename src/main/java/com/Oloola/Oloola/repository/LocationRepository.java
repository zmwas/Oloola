package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.Location;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
    Optional<Location> findByName(String name);
}
