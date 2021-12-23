package com.dians.navigation.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "fast_foods")
public class FastFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "longitude")
    private Double lon;
    @Column(name = "latitude")
    private Double lat;
    @Column
    private String name;

    public FastFood(Double lon, Double lat, String name) {
        this.lon = lon;
        this.lat = lat;
        this.name = name;
    }

    public FastFood() {
    }
}
