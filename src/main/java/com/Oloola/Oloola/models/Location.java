package com.Oloola.Oloola.models;

import com.Oloola.Oloola.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Trip.class, Views.Booking.class})
    Long id;

    @JsonView({Views.Trip.class, Views.Booking.class})
    String name;

    @JsonView({Views.Trip.class, Views.Booking.class})
    Double latitude;

    @JsonView({Views.Trip.class, Views.Booking.class})
    Double longitude;
    Point coordinates;
}
