package com.Oloola.Oloola.models;

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

public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne

    AppUser transporter;

    String licensePlateNumber;

    @OneToOne

    Driver driver;

    boolean isTrailer;

    Double availableTonage;

    String photoUrl;

    String insuranceSticker;

    String ntsaCertificateNumber;

}
