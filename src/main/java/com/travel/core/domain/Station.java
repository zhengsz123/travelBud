package com.travel.core.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "station_id_seq")
    private long id;

    @Column(name = "gas_location")
    private  String gasLocation;
    @Column (name = "gas_brand")
    private String gasBrand;

    private Gas gas;

    public long getId() {
        return id;
    }

    public String getGasBrand() {
        return gasBrand;
    }

    public void setGasBrand(String gasBrand) {
        this.gasBrand = gasBrand;
    }

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public String getGasLocation() {
        return gasLocation;
    }

    public void setGasLocation(String gasLocation) {
        this.gasLocation = gasLocation;
    }
}
