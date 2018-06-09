package com.travel.core.domain;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "gas")
public class Gas {


    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "gas_id_seq")
    private long id;

    @Column(name = "gas_price")
    private String gasPrice;
    @Column(name = "gas_location")
    private  String gasLocation;


    public long getId() {
        return id;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public String getGasLocation() {
        return gasLocation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public void setGasLocation(String gasLocation) {
        this.gasLocation = gasLocation;
    }
}