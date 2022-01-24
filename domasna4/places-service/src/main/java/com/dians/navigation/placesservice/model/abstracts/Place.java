package com.dians.navigation.placesservice.model.abstracts;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "longitude")
    protected Double lon;
    @Column(name = "latitude")
    protected Double lat;
    @Column
    protected String name;

    public Place() {

    }

    public Place(Double lon, Double lat, String name) {
        this.lon = lon;
        this.lat = lat;
        this.name = name;
    }
}
