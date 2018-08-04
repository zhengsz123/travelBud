package com.travel.core.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "gas")
public class Gas implements Serializable {


    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "gas_id_seq")
    @SequenceGenerator(name = "gas_id_seq",sequenceName = "gas_id_seq",allocationSize = 1)
    @JsonIgnore
    private long id;

    @Column(name = "gas_type")
    private String gasType;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "gas",cascade = CascadeType.ALL)
    private List<Station> stations;


    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public long getId() {
        return id;
    }

    public String getGasType() {
        return gasType;
    }

    public List<Station> getStations() {
        return stations;
    }
}