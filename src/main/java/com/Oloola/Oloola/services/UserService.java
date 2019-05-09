package com.Oloola.Oloola.services;

import com.Oloola.Oloola.auth.JwtTokenProvider;
import com.Oloola.Oloola.dto.AuthenticationRequest;
import com.Oloola.Oloola.dto.CreateUserDTO;
import com.Oloola.Oloola.exceptions.NotFoundException;
import com.Oloola.Oloola.models.AppUser;
import com.Oloola.Oloola.models.Auth;
import com.Oloola.Oloola.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Transactional
@Validated
public class UserService implements UserDetailsService {
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Autowired
    AuthenticationManager manager;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        manager.authenticate(authentication);
        String token = jwtTokenProvider.createToken(email, user.getRoles());
        Auth auth = new Auth();
        auth.setAppUser(user);
        auth.setToken(token);
        return auth;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }
}
