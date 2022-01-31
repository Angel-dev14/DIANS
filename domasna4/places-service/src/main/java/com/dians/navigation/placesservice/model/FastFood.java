package com.dians.navigation.placesservice.model;

import com.dians.navigation.placesservice.model.abstracts.Place;
import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "fast_foods")
public class FastFood extends Place {

    public FastFood(Double lon, Double lat, String name) {
        super(lon, lat, name);
    }

    public FastFood() {
        super();
    }
}
