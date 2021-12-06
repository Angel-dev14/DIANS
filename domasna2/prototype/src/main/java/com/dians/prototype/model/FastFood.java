package com.dians.prototype.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "fast_foods")
public class FastFood {

    @Id
    private Long id;
    @Column(name = "longitude")
    private Double lon;
    @Column(name = "latitude")
    private Double lat;
    @Column
    private String name;
}
