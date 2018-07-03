package com.travel.core.domain;

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

//    @OneToMany (fetch = FetchType.LAZY, mappedBy = "authorities",cascade = CascadeType.ALL)
//    private Collection<? extends GrantedAuthority> authorities;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private  String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "account_Expired")
    private Boolean accountExpired;
    @Column(name = "account_locked")
    private Boolean accountLocked;
    @Column(name = "credential_expired")
    private Boolean credentialExpired;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "email")
    private String email;

    @Transient
    private Collection<GrantedAuthority> authorities;

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialExpired(Boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public Boolean getCredentialExpired() {
        return credentialExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;

    }

    public long getId() {
        return id;
    }


    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities ;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }

    @Override
    public String getPassword() {
        return password;
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