package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
