package com.dians.navigation.model;

import lombok.Data;

@Data
public class Pub {
    private Long id;
    private Double lon;
    private Double lat;
    private String name;

    public Pub(Double lon, Double lat, String name) {
        this.lon = lon;
        this.lat = lat;
        this.name = name;
    }

    public Pub() {
    }
}

