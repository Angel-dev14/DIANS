package com.dians.navigation.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "pubs")
public class Pub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "longitude")
    private Double lon;
    @Column(name = "latitude")
    private Double lat;
    @Column
    private String name;

    public Pub(Double lon, Double lat, String name) {
        this.lon = lon;
        this.lat = lat;
        this.name = name;
    }

    public Pub() {
    }
}

