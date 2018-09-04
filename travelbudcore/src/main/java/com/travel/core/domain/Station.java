package com.travel.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "station")
public class Station {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = SEQUENCE, generator = "station_id_seq")
    @SequenceGenerator(name = "station_id_seq", sequenceName = "station_id_seq",allocationSize = 1)
    private long id;

    @Column(name = "gas_location")
    private  String gasLocation;
    @Column (name = "gas_brand")
    private String gasBrand;


    public long getId() {
        return id;
    }

    public String getGasBrand() {
        return gasBrand;
    }

    public void setGasBrand(String gasBrand) {
        this.gasBrand = gasBrand;
    }

    public String getGasLocation() {
        return gasLocation;
    }

    public void setGasLocation(String gasLocation) {
        this.gasLocation = gasLocation;
    }
}
