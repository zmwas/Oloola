package com.Oloola.Oloola.services;

import com.Oloola.Oloola.auth.JwtTokenProvider;
import com.Oloola.Oloola.dto.AuthenticationRequest;
import com.Oloola.Oloola.dto.CreateUserDTO;
import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Auth;
import com.Oloola.Oloola.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

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

    JwtTokenProvider jwtTokenProvider;


    public AuthenticationManager manager;

    public AppUser createUser(CreateUserDTO createUserDTO) {
        AppUser appUser = createUserDTO.from();
        return userRepository.save(appUser);
    }


    public AppUser findByEmail(String email) {
        Optional<AppUser> appUser = userRepository.findByEmail(email);
        if (!appUser.isPresent()) {
            throw new NotFoundException("user", String.valueOf(email));
        }

        return appUser.get();
    }

    public Auth authenticateUser(AuthenticationRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        AppUser user = findByEmail(email);
        manager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = jwtTokenProvider.createToken(email,user.getRoles());
        Auth auth = new Auth();
        auth.setAppUser(user);
        auth.setToken(token);
        return auth;
    }

}
