package com.dians.navigation.placesservice.model;

import com.dians.navigation.placesservice.model.abstracts.Place;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pubs")
public class Pub extends Place {

    public Pub(Double lon, Double lat, String name) {
        super(lon, lat, name);
    }

    public Pub() {
        super();
    }
}

