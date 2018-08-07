package com.travel.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "gasprices")
public class GasPrice implements Serializable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = SEQUENCE, generator = "station_id_seq")
    private long id;

    @Column(name = "gas_price")
    private String gasPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "gas_id")
    private Gas gas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "station_id")
    private Station station;

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public long getId() {
        return id;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public Gas getGas() {
        return gas;
    }

    public Station getStation() {
        return station;
    }
}
