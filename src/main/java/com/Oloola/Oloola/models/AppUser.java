package com.Oloola.Oloola.models;


import com.Oloola.Oloola.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {
    @JsonView({Views.Public.class, Views.Truck.class, Views.Driver.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JsonView({Views.Public.class, Views.Truck.class, Views.Driver.class, Views.Trip.class, Views.BookingDetails.class})
    String companyName;

    @JsonView({Views.Public.class, Views.Trip.class, Views.BookingDetails.class})
    String kraPin;

    @JsonView({Views.Public.class, Views.Truck.class, Views.Driver.class, Views.Trip.class, Views.BookingDetails.class})
    String email;

    @JsonView({Views.Public.class, Views.Truck.class, Views.Driver.class, Views.Trip.class, Views.BookingDetails.class})
    String phoneNumber;

    @JsonView(Views.Internal.class)
    private String password;
    @JsonView(Views.Public.class)
    String firebaseToken;

    @JsonView(Views.Public.class)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
