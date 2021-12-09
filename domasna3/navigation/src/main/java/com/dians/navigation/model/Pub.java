package com.dians.navigation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pubs")
public class Pub {
    @Id
    private Long id;
    @Column(name = "longitude")
    private Double lon;
    @Column(name = "latitude")
    private Double lat;
    @Column
    private String name;
}

