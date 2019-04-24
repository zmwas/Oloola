package com.Oloola.Oloola.services;

import com.Oloola.Oloola.dto.CreateUserDTO;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@Transactional
@Validated
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser createUser(CreateUserDTO createUserDTO) {
        AppUser appUser = createUserDTO.from();
        return userRepository.save(appUser);
    }
}
