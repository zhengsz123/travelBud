package com.travel.core.domain;
import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "gas")
public class Gas implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "gas_id_seq")
    @SequenceGenerator(name = "gas_id_seq",sequenceName = "gas_id_seq",allocationSize = 1)
    private long id;

    @Column(name = "gas_type")
    private String gasType;
    private Station station;

    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public long getId() {
        return id;
    }

    public String getGasType() {
        return gasType;
    }
}