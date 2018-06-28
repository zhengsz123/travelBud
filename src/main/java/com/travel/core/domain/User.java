package com.travel.core.domain;

import com.fasterxml.jackson.databind.node.BooleanNode;
import com.sun.javafx.beans.IDProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
   @GeneratedValue(strategy = SEQUENCE, generator = "users_id_seq")
   @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq")
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private  String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String passWord;
    @Column(name = "accountExpired")
    private Boolean accountExpired;
    @Column(name = "account_locked")
    private Boolean accountLocked;
    @Column(name = "credential_expired")
    private Boolean credentialExpired;
    @Column(name = "enabled")
    private Boolean enabled;


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

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}