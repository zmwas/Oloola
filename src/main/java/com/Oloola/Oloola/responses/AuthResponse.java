package com.Oloola.Oloola.responses;

import com.Oloola.Oloola.Views;
import com.Oloola.Oloola.models.Auth;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse extends Response{
    @JsonView(Views.Public.class)

    public Auth auth;
}
