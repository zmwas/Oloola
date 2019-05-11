package com.Oloola.Oloola.services;

import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@Validated
public class BaseService {


    private UserRepository userRepository;

    @Autowired
    public BaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser loggedInUser() {
        String username;
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<AppUser> appUser = userRepository.findByEmail(username);
        if (!appUser.isPresent()) {
            throw new NotFoundException("user", username);
        }
        return appUser.get();
    }

}
