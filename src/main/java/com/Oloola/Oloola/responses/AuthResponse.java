package com.Oloola.Oloola.responses;

import com.Oloola.Oloola.models.Auth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse extends Response{
    public Auth auth;
}
