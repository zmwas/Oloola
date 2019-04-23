package com.Oloola.Oloola.repository;

import com.Oloola.Oloola.models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {
}
