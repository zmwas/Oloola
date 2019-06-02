package com.Oloola.Oloola.models;

import com.Oloola.Oloola.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    @JsonView(Views.Public.class)
    public String token;
    @JsonView(Views.Public.class)
    public AppUser appUser;

}
