package com.travel.core.domain;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
public class User {


    @Id
   @GeneratedValue(strategy = SEQUENCE, generator = "users_id_seq")
   @SequenceGenerator(name = "users_id_seq", sequenceName = "users_seq")
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private  String lastName;

//    @Column(name ="gas_quality")
//    private String gasQuality;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}

