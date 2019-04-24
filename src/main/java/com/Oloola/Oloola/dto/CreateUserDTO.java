package com.Oloola.Oloola.dto;


import com.Oloola.Oloola.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        AppUser appUser = new AppUser();
        appUser.setCompanyName(companyName);
        appUser.setEmail(email);
        appUser.setKraPin(kraPin);
        appUser.setPhoneNumber(phoneNumber);
        appUser.setPassword(password);
        appUser.setRole(role);
        return appUser;
    }
}
