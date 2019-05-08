package com.Oloola.Oloola.dto;


import com.Oloola.Oloola.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    String companyName;

    String kraPin;

    String email;

    String phoneNumber;

    String password;

    String role;

    public AppUser from() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AppUser appUser = new AppUser();
        appUser.setCompanyName(companyName);
        appUser.setEmail(email);
        appUser.setKraPin(kraPin);
        appUser.setPhoneNumber(phoneNumber);
        appUser.setPassword(encoder.encode(password));
        appUser.setRoles(Collections.singletonList(role));
        return appUser;
    }
}
