package com.Oloola.Oloola.dto;


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

}
